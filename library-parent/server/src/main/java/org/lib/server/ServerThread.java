/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.protocol.Disconnect;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ServerThread extends Thread {

    public ServerThread() {
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable thrwbl) {
                System.out.println("internal error!!!");
            }
        });
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(3456);
            Logger.getLogger(ServerThread.class.getName()).log(Level.INFO, "waiting for client");
            try (Socket s = ss.accept();
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())) {
                for (;;) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.INFO, "waiting for command");

                    LibraryCommand comm = (LibraryCommand) ois.readObject();
                    Logger.getLogger(ServerThread.class.getName()).log(Level.INFO, "command: " + comm);
                    if (comm instanceof Disconnect) {
                        break;
                    }
                    Object result;
                    try {
                        result = comm.execute(LibraryFacade.getDefault());
                    } catch (LibraryException ex) {
                        result = ex;

                    }
                    oos.writeObject(result);
                    oos.flush();

                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}