/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.net.InetAddress;
import org.lib.connection.impl.ConnectionServiceImpl;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class ConnectionService {

    public static final int DEFAULT_PORT = 3456;
    
    private static ConnectionService instance;
    private static ServiceTracker serviceTracker;

    public static ConnectionService getDefault() {
        if (instance == null) {
            instance = (ConnectionService) serviceTracker.getService();
            if (instance == null) {
                instance = new ConnectionServiceImpl();
            }
        }
        return instance;
    }

    public static void setServiceTracker(ServiceTracker aServiceTracker) {
        serviceTracker = aServiceTracker;
    }

    public ConnectionService() {
    }

    public abstract void connect(InetAddress ia, int port) throws LibraryException;

    public abstract void disconnect();

    public abstract <T> T send(LibraryCommand libraryCommand) throws LibraryException;

    public abstract boolean isConnected();
}
