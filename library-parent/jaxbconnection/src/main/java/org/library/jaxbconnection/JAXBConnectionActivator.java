package org.library.jaxbconnection;

import org.library.impl.JAXBConnection;
import org.lib.connection.ConnectionService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JAXBConnectionActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        context.registerService(ConnectionService.class, new JAXBConnection(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
