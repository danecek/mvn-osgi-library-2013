/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.model.Address;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class Connection {

    Socket s;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public void connect(InetAddress ia, int port) {
        try {
            s = new Socket(ia, port);
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            MainFrame.getInstance().showError(ex);
        }


    }

    public void disconnect() {
    }
    static Class[] preloaded = {
        Reader.class,
        Book.class,
        ReaderId.class,
        BookId.class,
        Address.class,};

    private Connection() {
    }

    public static Connection getInstance() {
        return ConnectionHolder.INSTANCE;
    }

    private static class ConnectionHolder {

        private static final Connection INSTANCE = new Connection();
    }

    public Object send(LibraryCommand libraryCommand) throws LibraryException {
        try {
            oos.writeObject(libraryCommand);
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }



    }
}
