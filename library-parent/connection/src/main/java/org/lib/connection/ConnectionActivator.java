package org.lib.connection;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.view.MainFrame;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class ConnectionActivator implements BundleActivator {

    ServiceTracker serviceTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        MainFrame.getInstance().addActionToMenu(new ConnectAction());
        MainFrame.getInstance().addActionToMenu(new DisconnectAction());
        MainFrame.getInstance().addActionToMenu(new RefreshAction());
        serviceTracker = new ServiceTracker(context, ConnectionService.class, null);
        serviceTracker.open();
        ConnectionService.setServiceTracker(serviceTracker);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        ConnectionService.getDefault().disconnect();
    }
}
