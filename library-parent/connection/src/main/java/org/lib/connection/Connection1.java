/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
public class Connection1 {

    Socket s;
    OutputStream oos;
    InputStream ois;

    public void connect(InetAddress ia, int port) {
        try {
            s = new Socket(ia, port);
            ois = s.getInputStream();
            oos = s.getOutputStream();
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

    private Connection1() {
    }

    public static Connection1 getInstance() {
        return ConnectionHolder.INSTANCE;
    }

    private static class ConnectionHolder {

        private static final Connection1 INSTANCE = new Connection1();
    }

    private static byte[] ser(LibraryCommand libraryCommand) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bs);
            oos.writeObject(libraryCommand);
            oos.flush();
            return bs.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Connection1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    private static Object deSer(byte[] buff, int len) {
        try {
            ByteArrayInputStream bs = new ByteArrayInputStream(buff, 0, len);
            ObjectInputStream oos = new ObjectInputStream(bs);
            return oos.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Connection1.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    byte[] buff = new byte[10000];

    public Object send(LibraryCommand libraryCommand) throws LibraryException {
        try {
            oos.write(ser(libraryCommand));
            oos.flush();
            int len = ois.read(buff);
            Object response = deSer(buff, len);
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Connection1.class.getName()).log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        }
    }
}
