package org.lib.derbydb;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.derbydb.impl.DerbyDAOFactory;
import org.lib.integration.AbstractDAOFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DerbyDBActivator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        context.registerService(AbstractDAOFactory.class,
                new DerbyDAOFactory(context), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
