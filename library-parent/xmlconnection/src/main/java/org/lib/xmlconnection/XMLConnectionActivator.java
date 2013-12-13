package org.lib.xmlconnection;

import org.lib.xmlconnection.impl.JAXBConnection;
import org.lib.connection.ConnectionService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMLConnectionActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        context.registerService(ConnectionService.class.getName(), new JAXBConnection(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
