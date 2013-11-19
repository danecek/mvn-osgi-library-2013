/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import org.lib.model.Book;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.view.impl.Refreshable;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {

    private static MainFrame instance;
    static Collection<Refreshable> rf = new ArrayList<>();
    static Collection<SetEnable> tec = new ArrayList<>();

    /**
     * @return the instance
     */
    public static MainFrame getInstance() {
        return instance;
    }
    BundleContext context;

    public static void init(BundleContext context, JMenuBar menuBar) {
        instance = new MainFrame(context, menuBar);
    }

    public void exit() {
        try {
            Bundle bnd = context.getBundle(0);
            Framework fw = (Framework) bnd;
            fw.stop();
            fw.waitForStop(0);
        } catch (BundleException | InterruptedException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.INFO, null, ex);
        }
    }
    MainPanel mainPanel;

    public MainFrame(BundleContext context, JMenuBar menuBar) {
        super(Messages.Main_Frame.cm());
        this.context = context;
        setJMenuBar(menuBar);

        add(mainPanel = new MainPanel());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exit();
            }
        });

        setBounds(300, 300, 800, 400);
        setVisible(true);

    }

    public void showError(LibraryException ex) {
        showError(ex.toString());
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void refresh() {
        for (Refreshable r : rf) {
            try {
                r.refresh();
            } catch (LibraryException ex) {
                showError(ex);
            }
        }
        actionsNotif();

    }

    public static void addRefreshable(Refreshable r) {
        rf.add(r);
    }

    public static void addTestEnable(SetEnable te) {
        tec.add(te);

    }

    public Book getSelectedBook() {
        return mainPanel.getSelectedBook();
    }

    public void actionsNotif() {
        for (SetEnable te : tec) {
            te.setEnable();
        }
    }
}
