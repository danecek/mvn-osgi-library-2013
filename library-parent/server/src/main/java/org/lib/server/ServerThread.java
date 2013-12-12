/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.protocol.LibraryCommand;

/**
 *
 * @author danecek
 */
public class ServerThread extends Thread {

    static Class[] preloaded = {
        Book.class,
        BookId.class
    };
    private ServerSocket ss;
    ExecutorService pool = Executors.newFixedThreadPool(1000);

    public ServerThread() {
        try {
            ss = new ServerSocket(LibraryCommand.PORT);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Logger.getLogger(ServerThread.class.getName()).log(Level.INFO, "waiting for client");
                Socket s = ss.accept();
                pool.execute(new ClientTask(s));
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
