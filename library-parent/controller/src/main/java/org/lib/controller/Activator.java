package org.lib.controller;

import org.lib.controller.actions.CreateBookAction;
import org.lib.controller.actions.DeleteBookAction;
import org.lib.controller.actions.ExitAction;
import org.lib.controller.actions.RefreshAction;
import org.lib.view.MainFrame;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
      MainFrame.getInstance().addLibraryAction(new ExitAction());
      MainFrame.getInstance().addLibraryAction(new CreateBookAction());
      MainFrame.getInstance().addLibraryAction(new DeleteBookAction());
      MainFrame.getInstance().addLibraryAction(new RefreshAction());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
