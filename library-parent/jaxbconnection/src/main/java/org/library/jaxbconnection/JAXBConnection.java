/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.jaxbconnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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

    private Reader ois;
    private DataOutputStream oos;
    private Socket socket;
    JAXBContext jc;
    Marshaller m;
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
            m = jc.createMarshaller();
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
            ois = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);

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

    @Override
    public Object send(LibraryCommand libraryCommand) throws LibraryException {
        if (!isConnected()) {
            throw new LibraryException("no connection"); // todo
        }
        try {
            StringWriter sw;
            m.marshal(libraryCommand, sw = new StringWriter());
            String sc = sw.toString();
            System.out.println(sc);
            oos.writeInt(sc.length());
            oos.writeBytes(sc);
            
            oos.flush();

            char[] cbuf = new char[1000];
            logger.log(Level.INFO, "waiting for response");
            int l = ois.read(cbuf);
            Object response = u.unmarshal(new StringReader(new String(cbuf, 0, l)));
            logger.log(Level.INFO, "response: " + response);
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return response;
        } catch (IOException ex) {
            logger.log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
