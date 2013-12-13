/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacadeInterface;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.protocol.Disconnect;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ClientTask implements Runnable {

    static final Logger logger = Logger.getLogger(ClientTask.class.getName());
    static Class[] preloaded = {
        Book.class,
        BookId.class
    };
    Socket socket;
    LibraryFacadeInterface facade;

    public ClientTask(Socket s, LibraryFacadeInterface facade) {
        this.socket = s;
        this.facade = facade;
    }

    @Override
    public void run() {

        try (Socket s = this.socket;
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {
            for (;;) {
                logger.log(Level.INFO, "waiting for command");
                LibraryCommand comm = (LibraryCommand) ois.readObject();
                logger.log(Level.INFO, "command: {0}", comm.toString());
                if (comm instanceof Disconnect) {
                    break;
                }
                Object result;
                try {
                    result = comm.execute(facade);
                } catch (LibraryException ex) {
                    result = ex;
                }
                logger.log(Level.INFO, "result: {0}", result.toString());
                oos.writeObject(result);
                oos.flush();
            }
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
