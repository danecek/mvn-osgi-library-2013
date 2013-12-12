package org.lib.nioserver;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class NIOServerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
                Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
       new Thread(new NIOServer(3456)).start();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
