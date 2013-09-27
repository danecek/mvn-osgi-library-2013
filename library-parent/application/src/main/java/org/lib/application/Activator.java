package org.lib.application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("xxx");
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
