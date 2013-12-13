package org.lib.server;

import org.lib.server.impl.ServerThread;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class ServerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        ServiceTracker st = new ServiceTracker(context, CreateClientTaskService.class, null);
        st.open();
        CreateClientTaskService.setSt(st);             
        new ServerThread();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
