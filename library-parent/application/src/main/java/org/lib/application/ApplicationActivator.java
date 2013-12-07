package org.lib.application;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.view.MainFrame;
import javax.swing.SwingUtilities;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ApplicationActivator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, getClass().getName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setContext(context);
                MainFrame.getInstance().setVisible(true);
                MainFrame.getInstance().notifyActions();
            }
        });
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }
}
