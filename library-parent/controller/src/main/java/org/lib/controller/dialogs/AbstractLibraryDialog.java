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

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryDialog extends JDialog implements Validator {

    JLabel errorLabel = new JLabel();
    Box buttonBox = new Box(BoxLayout.X_AXIS);
    JPanel content = new JPanel();

    public AbstractLibraryDialog(String title) {
        super((JDialog) null, title, true);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(new JButton(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.dispose();
            }
        }));
        buttonBox.add(new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AbstractLibraryDialog.this.okAction();
                AbstractLibraryDialog.this.dispose();
            }
        }));
        add(errorLabel, BorderLayout.NORTH);
        add(content);
        add(buttonBox, BorderLayout.SOUTH);
        
    }

    void error(String message) {
        errorLabel.setText(message);

    }

    void clearError() {
        errorLabel.setText("");
    }
    
    abstract void okAction();
}
