/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.application;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.lib.utils.Messages;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {

    public MainFrame(final BundleContext context) {
        super(Messages.Main_Frame.cm());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    Bundle bnd = context.getBundle(0);
                    Framework fw = (Framework) bnd;
                    fw.stop();
                    fw.waitForStop(0);
                } catch (BundleException | InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.INFO, null, ex);
                }
            }
        });

        setBounds(300, 300, 800, 400);
        setVisible(true);

    }
}
