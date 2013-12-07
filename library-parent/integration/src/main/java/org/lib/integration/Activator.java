package org.lib.integration;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        ServiceTracker st = new ServiceTracker(context, AbstractDAOFactory.class, null);
        st.open();// !!!
        AbstractDAOFactory.setSt(st);

    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
