package org.lib.proxy;

import org.lib.business.LibraryFacade;
import org.lib.proxy.impl.ConnectAction;
import org.lib.proxy.impl.LibraryFacadeProxy;
import org.lib.view.MainFrame;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(LibraryFacade.class,
                new LibraryFacadeProxy(), null);
        MainFrame.getInstance().addLibraryAction(new ConnectAction());
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
