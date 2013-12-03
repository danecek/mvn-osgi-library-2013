package org.lib.business;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    
    public void start(BundleContext context) throws Exception {
        ServiceTracker st = new ServiceTracker(context, LibraryFacade.class, null);
        st.open();
        LibraryFacade.setSt(st);
    }
    
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
