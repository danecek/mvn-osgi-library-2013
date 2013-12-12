/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection.impl;

import org.lib.connection.ConnectionService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.model.Reader;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class ConnectionServiceImpl extends ConnectionService {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Socket socket;
    private static Class[] preloaded = {
        Reader.class,};

    @Override
    public boolean isConnected() {
        return socket != null;
    }

    @Override
    public void connect(InetAddress ia, int port) throws LibraryException {
        try {
            socket = new Socket(ia, port);
           // socket.setSoTimeout(3000); !!!
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void disconnect() {
        if (isConnected()) {
            try {
                oos.close();
                ois.close();
                socket.close();
                socket = null;
            } catch (IOException ex) {
                Logger.getLogger(ConnectionServiceImpl.class.getName()).log(Level.INFO, null, ex);
            }
        }
    }

    public ConnectionServiceImpl() {
    }

    @Override
    public Object send(LibraryCommand libraryCommand) throws LibraryException {
        if (!isConnected()) {
            throw new LibraryException(Messages.No_conection.cm());
        }
        try {
            oos.writeObject(libraryCommand);
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return response;
        } catch (IOException ex) {
            Logger.getLogger(ConnectionServiceImpl.class.getName()).log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}