package org.lib.proxy;

import org.lib.business.LibraryFacade;
import org.lib.proxy.impl.LibraryFacadeProxy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(LibraryFacade.class, 
                new LibraryFacadeProxy(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
