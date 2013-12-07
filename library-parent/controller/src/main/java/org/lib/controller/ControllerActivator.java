package org.lib.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.controller.actions.CreateBookAction;
import org.lib.controller.actions.DeleteBookAction;
import org.lib.controller.actions.ExitAction;
import org.lib.view.MainFrame;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ControllerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        MainFrame.getInstance().addActionToMenu(new ExitAction());
        MainFrame.getInstance().addActionToMenu(new CreateBookAction());
        MainFrame.getInstance().addActionToMenu(new DeleteBookAction());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
