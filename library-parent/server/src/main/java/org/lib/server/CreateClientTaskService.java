/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.server;

import java.net.Socket;
import org.lib.business.LibraryFacadeInterface;
import org.lib.server.impl.CreateClientTaskDefault;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class CreateClientTaskService {

    static CreateClientTaskService instance;
    private static ServiceTracker st;

    public static CreateClientTaskService getDefault() {
        if (instance == null) {
            instance = (CreateClientTaskService) st.getService();
            if (instance == null) {
                instance = new CreateClientTaskDefault();
            }
        }
        return instance;
    }

    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }

    public abstract Runnable createClientTask(Socket socket, LibraryFacadeInterface facade);
}
