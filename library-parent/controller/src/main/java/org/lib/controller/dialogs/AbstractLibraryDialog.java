/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryDialog extends JDialog implements Validator {

    JLabel errorLabel = new JLabel();
    Box buttonBox = new Box(BoxLayout.X_AXIS);
    private JPanel content = new JPanel();
    private final AbstractAction okAction;

    public AbstractLibraryDialog(String title) {
        super(MainFrame.getInstance(), title, true);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(new JButton(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.dispose();
            }
        }));
        okAction = new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.okAction();
                AbstractLibraryDialog.this.dispose();
            }
        };
        buttonBox.add(new JButton(okAction));
        add(errorLabel, BorderLayout.NORTH);
        add(content);
        add(buttonBox, BorderLayout.SOUTH);
        setLocation(MainFrame.getInstance().getLocation());

    }

    public void error(String message) {
        errorLabel.setText(message);
        okAction.setEnabled(false);

    }

    public void clearError() {
        errorLabel.setText("");
        okAction.setEnabled(true);
    }

    public abstract void okAction();

    /**
     * @return the content
     */
    public JPanel getContent() {
        return content;
    }
}
