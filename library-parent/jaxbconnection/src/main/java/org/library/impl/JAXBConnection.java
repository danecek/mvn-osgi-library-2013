/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import org.lib.connection.ConnectionService;
import org.lib.protocol.Books;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBooks;
import org.lib.protocol.Disconnect;
import org.lib.protocol.GetBooks;
import org.lib.protocol.GetReaders;
import org.lib.protocol.LibraryCommand;
import org.lib.protocol.Ok;
import org.lib.protocol.Readers;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class JAXBConnection extends ConnectionService {

    private DataInputStream ois;
    private DataOutputStream oos;
    private Socket socket;
    JAXBContext jc;
    Marshaller marshaller;
    Unmarshaller u;
    private static Class[] preloaded = {
        Reader.class,};
    static final Logger logger = Logger.getLogger(JAXBConnection.class.getName());

    @Override
    public boolean isConnected() {
        return socket != null;
    }

    public JAXBConnection() {
        try {
            jc = JAXBContext.newInstance(GetReaders.class,
                    GetBooks.class,
                    Readers.class,
                    Books.class,
                    CreateBook.class,
                    DeleteBooks.class,
                    Ok.class);
            marshaller = jc.createMarshaller();
            u = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void connect(InetAddress ia, int port) throws LibraryException {
        try {
            socket = new Socket(ia, port);
            //   socket.setSoTimeout(3000);
            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            send(new Disconnect());
            oos.close();
            ois.close();
            socket.close();
            socket = null;
        } catch (IOException | LibraryException ex) {
            logger.log(Level.INFO, null, ex);
        }
    }

    public static void marshallObject(Object object, Marshaller marshaller, DataOutputStream oos) throws JAXBException, IOException {
        //  StringWriter sw = new StringWriter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(object, baos);
        //     String xmlcommand = sw.toString();
        byte[] buff = baos.toByteArray();// xmlcommand.getBytes(StandardCharsets.UTF_8);
        oos.writeInt(buff.length);
        oos.write(buff);
        oos.flush();
    }

    public static Object unmarshalMessage(DataInputStream ois, Unmarshaller u) throws IOException, JAXBException {
        int len = ois.readInt();
        byte[] buf = new byte[len];
        ois.readFully(buf);
        String xmlMessage = new String(buf, StandardCharsets.UTF_8);
        logger.log(Level.INFO, "xml command: {0}", xmlMessage);
        Object message = u.unmarshal(new StringReader(xmlMessage));//   new ByteArrayInputStream((buf)));
        logger.log(Level.INFO, "binary command: {0}", message);
        return message;
    }

    @Override
    public <T> T send(LibraryCommand libraryCommand) throws LibraryException {
        if (!isConnected()) {
            throw new LibraryException("no connection"); // todo
        }
        try {
//            StringWriter sw = new StringWriter();
//            marshaller.marshal(libraryCommand, sw);
//            String xmlcommand = sw.toString();
//            System.out.println(xmlcommand);
//            byte[] c = xmlcommand.getBytes(StandardCharsets.UTF_8);
//            oos.writeInt(xmlcommand.length());
//            oos.writeBytes(xmlcommand);
//
//            oos.flush();
            marshallObject(libraryCommand, marshaller, oos);
            Object response = unmarshalMessage(ois, u);

//            char[] cbuf = new char[1000];
//            logger.log(Level.INFO, "waiting for response");
//            int len = ois.read(cbuf);
//            Object response = u.unmarshal(new StringReader(new String(cbuf, 0, len)));
            logger.log(Level.INFO, "response: " + response);
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return (T) response;
        } catch (IOException ex) {
            logger.log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
