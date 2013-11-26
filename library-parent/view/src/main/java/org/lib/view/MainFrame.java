/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
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
    Collection<SetEnable> tec = new ArrayList<>();

    /**
     * @return the instance
     */
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
    private BundleContext context;

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
    JMenuBar menuBar;

    public MainFrame() {
        super(Messages.Main_Frame.cm());
        setJMenuBar(menuBar = new JMenuBar());

        add(mainPanel = new MainPanel());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exit();
            }
        });

        setBounds(300, 300, 800, 400);

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

    public void addTestEnable(SetEnable te) {
        tec.add(te);
        JMenu mn;
        JMenuBar mnb = getJMenuBar();
        for (int i = 0; i < mnb.getMenuCount(); i++) {
            mn = mnb.getMenu(i);
            if (mn.getName().equals(te.getMenuName())) {
                mn.add((Action) te);
                return;
            }
        }
        mn = new JMenu(te.getMenuName());
        mnb.add(mn);
        mn.add((Action) te);
        

    }

    public Book getSelectedBook() {
        return mainPanel.getSelectedBook();
    }

    public void actionsNotif() {
        for (SetEnable te : tec) {
            te.setEnable();
        }
    }

    /**
     * @param context the context to set
     */
    public void setContext(BundleContext context) {
        this.context = context;
    }
}
