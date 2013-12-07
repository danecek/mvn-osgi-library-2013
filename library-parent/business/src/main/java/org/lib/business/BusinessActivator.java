package org.lib.business;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class BusinessActivator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        ServiceTracker st = new ServiceTracker(context, LibraryFacade.class, null);
        st.open();
        LibraryFacade.setSt(st);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
