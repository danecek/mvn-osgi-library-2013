/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import org.lib.utils.LibraryAction;
import org.lib.view.impl.MainPanel;
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
    private Collection<LibraryAction> actions = new ArrayList<>();
    private MainPanel mainPanel;
    private JMenuBar menuBar;
    private BundleContext context;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }

        return instance;
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

    public MainFrame() {
        super(Messages.Main_Frame.cm());
        menuBar = new JMenuBar();
        menuBar.add(new JMenu(Messages.File.cm()));
        setJMenuBar(menuBar);

        add(mainPanel = new MainPanel());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exit();
            }
        });
        setBounds(300, 300, 800, 400);
    }

    public void showError(Exception ex) {
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
        notifyActions();
    }

    public static void addRefreshable(Refreshable r) {
        rf.add(r);
    }

    public void addActionToMenu(LibraryAction libraryAction) {
        actions.add(libraryAction);
        JMenu mnu;
        JMenuBar mnuBar = getJMenuBar();
        for (int i = 0; i < mnuBar.getMenuCount(); i++) {
            mnu = mnuBar.getMenu(i);
            if (mnu.getText().equals(libraryAction.getMenuName())) {
                mnu.add((Action) libraryAction);
                return;
            }
        }
        mnu = new JMenu(libraryAction.getMenuName());
        mnuBar.add(mnu);
        mnu.add((Action) libraryAction);
    }

    public Collection<Book> getSelectedBooks() {
        return mainPanel.getSelectedBooks();
    }

    public void notifyActions() {
        for (LibraryAction te : actions) {
            te.setEnabled();
        }
    }

    public void setContext(BundleContext context) {
        this.context = context;
    }
}
