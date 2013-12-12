/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.xmlconnection.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lib.connection.ConnectionService;
import org.lib.protocol.Disconnect;
import org.lib.protocol.LibraryCommand;
import org.lib.protocol.Ok;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.xmlconnection.JAXBUtils;

/**
 *
 * @author danecek
 */
public class JAXBConnection extends ConnectionService {

    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    JAXBContext jc;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    private static Class[] preloaded = {
        Reader.class,};
    static final Logger logger = Logger.getLogger(JAXBConnection.class.getName());

    @Override
    public boolean isConnected() {
        return socket != null;
    }

    public JAXBConnection() {
        try {
            jc = JAXBUtils.createJAXBContext();
            marshaller = jc.createMarshaller();
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void connect(InetAddress ia, int port) throws LibraryException {
        try {
            socket = new Socket(ia, port);
           // socket.setSoTimeout(3000);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

        } catch (IOException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            send(new Disconnect());
            dos.close();
            dis.close();
            socket.close();
            socket = null;
        } catch (IOException | LibraryException ex) {
            logger.log(Level.INFO, null, ex);
        }
    }

    @Override
    public <T> T send(LibraryCommand libraryCommand) throws LibraryException {
        if (!isConnected()) {
            throw new LibraryException(Messages.No_connection.cm());
        }
        try {
            JAXBUtils.marshallMessage(libraryCommand, marshaller, dos);
            if (libraryCommand instanceof Disconnect) {
                return (T) new Ok();
            }
            Object response = JAXBUtils.unmarshalMessage(unmarshaller, dis);
            logger.log(Level.INFO, "response: {0}", response);
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
