/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import org.lib.utils.Validator;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryDialog extends JDialog implements Validator {

    private JLabel errorLabel;
    protected JPanel content = new JPanel();

    final JComponent createErrorPanel() {
        Box errPanel = new Box(BoxLayout.X_AXIS);
        int i = 5;
        errPanel.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        errPanel.add(errorLabel=new JLabel());
        return errPanel;
    }

    final JComponent createButtonPanel() {
        Box btnPanel = new Box(BoxLayout.X_AXIS);
        int i = 5;
        btnPanel.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(new JButton(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.dispose();
            }
        }));
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.okAction();
                AbstractLibraryDialog.this.dispose();
            }
        }));
        return btnPanel;
    }

    public AbstractLibraryDialog(String title) {
        super(MainFrame.getInstance(), title, true);
        int i = 5;
        content.setBorder(BorderFactory.createEmptyBorder(i, i, i, i));
        add(createErrorPanel(), BorderLayout.NORTH);
        add(content, BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.SOUTH);
        Point loc = MainFrame.getInstance().getLocation();
        loc.translate(200, 100);
        setLocation(loc);

    }

    public void error(String message) {
        errorLabel.setText("Error: " + message);

    }

    public void clearError() {
        errorLabel.setText("");
        okAction.setEnabled(true);
    }

    public abstract void okAction();
}
