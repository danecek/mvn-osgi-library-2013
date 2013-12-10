/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacadeInterface;
import org.lib.business.LibraryFacadeService;
import org.lib.integration.DAOFactoryService;
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

    Socket s;
    static Class[] preloaded = {
        Book.class,
        BookId.class
    };

    public ClientTask(Socket s) {
        this.s = s;
    }

    static LibraryFacadeInterface createFacadeProxy() {
        InvocationHandler ih = new InvocationHandler() {
            Lock lock = new ReentrantLock();

            @Override
            public Object invoke(Object o, Method method, Object[] os) throws Throwable {
                Logger.getLogger(ClientTask.class.getName()).log(Level.INFO, "business: {0}", method.getName());
                lock.lock();
                Object response = null;
                try {
                    response = method.invoke(LibraryFacadeService.getDefault(), os);
                    DAOFactoryService.getDefault().commit();
                } catch (Exception ex) {
                    DAOFactoryService.getDefault().rollback();
                } finally {
                    lock.unlock();
                }
                Logger.getLogger(ClientTask.class.getName()).log(Level.INFO, "response: {0}", response);

                return response;
            }
        };
        return (LibraryFacadeInterface) Proxy.newProxyInstance(LibraryFacadeInterface.class.getClassLoader(),
                new Class<?>[]{LibraryFacadeInterface.class}, ih);
    }

    @Override
    public void run() {
        try {
            try (Socket s = this.s;
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream())) {
                LibraryFacadeInterface proxyFacade = createFacadeProxy();
                for (;;) {
                    Logger.getLogger(ClientTask.class.getName()).log(Level.INFO, "waiting for command");
                    LibraryCommand comm = (LibraryCommand) ois.readObject();
                    if (comm instanceof Disconnect) {
                        break;
                    }
                    Object result;
                    try {
                        result = comm.execute(proxyFacade);
                    } catch (LibraryException ex) {
                        result = ex;
                    }
                    oos.writeObject(result);
                    oos.flush();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
