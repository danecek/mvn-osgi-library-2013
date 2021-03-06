/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import org.lib.utils.Validator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryDialog extends JDialog implements Validator {

    private JLabel errorLabel;
    private JPanel content = new JPanel();
    private Action okAction;

    final JComponent createErrorPanel() {
        Box errPanel = new Box(BoxLayout.X_AXIS);
        errPanel.setPreferredSize(new Dimension(300, 30));
        int i = 5;
        errPanel.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        errPanel.add(errorLabel = new JLabel("     "));
        errorLabel.setForeground(Color.red);
        return errPanel;
    }

    final JComponent createButtonPanel() {
        Box btnPanel = new Box(BoxLayout.X_AXIS);
        int i = 5;
        btnPanel.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(new JButton(new AbstractAction(Messages.Cancel.cm()) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.dispose();
            }
        }));
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(new JButton(okAction = new AbstractAction(Messages.OK.cm()) {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    AbstractLibraryDialog.this.okAction();
                    AbstractLibraryDialog.this.dispose();
                } catch (LibraryException ex) {
                    MainFrame.getInstance().showError(ex);
                }
            }
        }));
        return btnPanel;
    }

    public AbstractLibraryDialog(String title) {
        super(MainFrame.getInstance(), title, true);
        int i = 5;
        JPanel interior = new JPanel(new BorderLayout(i, i));
        interior.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        add(interior);
        //   content.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        interior.add(createErrorPanel(), BorderLayout.PAGE_START);
        interior.add(content, BorderLayout.LINE_START);
        interior.add(createButtonPanel(), BorderLayout.PAGE_END);
        Point loc = MainFrame.getInstance().getLocation();
        loc.translate(200, 100);
        setLocation(loc);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AbstractLibraryDialog.this.dispose();
            }
        });
    }

    public void error(String message) {
        errorLabel.setText(Messages.Error.cm() + ": " + message);
    }

    public void clearError() {
        errorLabel.setText("");
        okAction.setEnabled(true);
    }

    public abstract void okAction() throws LibraryException;

    /**
     * @return the content
     */
    public JPanel getContent() {
        return content;
    }
}
