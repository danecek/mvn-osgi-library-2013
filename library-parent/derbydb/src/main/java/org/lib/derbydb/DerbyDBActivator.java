package org.lib.derbydb;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.derbydb.impl.DerbyDAOFactory;
import org.lib.integration.DAOFactoryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DerbyDBActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        context.registerService(DAOFactoryService.class.getName(),
                new DerbyDAOFactory(context), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
