/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.lib.utils.Validator;

/**
 *
 * @author danecek
 */
public class ValidatedTF extends JTextField {

    public ValidatedTF(final Validator validator) {
        this(validator, "");
    }
    public ValidatedTF(final Validator validator, String text) {
        super(text);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                validator.validateDialog();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                validator.validateDialog();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                validator.validateDialog();
            }
        });
    }
}
