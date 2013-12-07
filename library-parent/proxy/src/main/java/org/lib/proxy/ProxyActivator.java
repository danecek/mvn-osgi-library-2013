package org.lib.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.proxy.impl.LibraryFacadeProxy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProxyActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        context.registerService(LibraryFacade.class,
                new LibraryFacadeProxy(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
