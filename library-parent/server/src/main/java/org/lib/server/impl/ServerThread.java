/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server.impl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacadeInterface;
import org.lib.business.LibraryFacadeService;
import org.lib.integration.DAOFactoryService;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.protocol.LibraryCommand;
import org.lib.server.CreateClientTaskService;

/**
 *
 * @author danecek
 */
public class ServerThread extends Thread {

    static final Logger logger = Logger.getLogger(ServerThread.class.getName());
    static Class[] preloaded = {
        Book.class,
        BookId.class
    };
    private ServerSocket ss;
    ExecutorService pool;

    static LibraryFacadeInterface createServerFacadeProxy() {
        InvocationHandler ih = new InvocationHandler() {
            Lock lock = new ReentrantLock();

            @Override
            public Object invoke(Object o, Method method, Object[] os) throws Throwable {
                logger.log(Level.INFO, "business: {0}", method.getName());
                lock.lock();
                Object response = null;
                try {
                    response = method.invoke(LibraryFacadeService.getDefault(), os);
                    logger.log(Level.INFO, "response: {0}", response);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } finally {
                    lock.unlock();
                }
                return response;
            }
        };
        return (LibraryFacadeInterface) Proxy.newProxyInstance(LibraryFacadeInterface.class.getClassLoader(),
                new Class<?>[]{LibraryFacadeInterface.class}, ih);
    }

    public ServerThread() {
        pool = Executors.newFixedThreadPool(1000);
        try {
            ss = new ServerSocket(LibraryCommand.PORT);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        pool.submit(this);
    }

    @Override
    public void run() {
        for (;;) {
            try {
                logger.log(Level.INFO, "waiting for client");
                Socket s = ss.accept();
                pool.execute(CreateClientTaskService.getDefault().createClientTask(s, createServerFacadeProxy()));
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }

    }
}
