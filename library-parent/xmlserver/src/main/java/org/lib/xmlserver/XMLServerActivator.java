package org.lib.xmlserver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.xmlserver.impl.CreateXMLClientTask;
import org.lib.server.CreateClientTaskService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMLServerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        context.registerService(CreateClientTaskService.class.getName(), new CreateXMLClientTask(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
