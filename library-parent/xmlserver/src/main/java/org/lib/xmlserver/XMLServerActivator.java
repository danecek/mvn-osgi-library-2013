package org.lib.xmlserver;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class XMLServerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        new XMLServerThread().start();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
