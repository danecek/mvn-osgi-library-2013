package org.lib.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
      ServiceTracker st =  new ServiceTracker(context, AbstractDAOFactory.class, null);
      st.open();// !!!
      AbstractDAOFactory.setSt(st);
        
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
