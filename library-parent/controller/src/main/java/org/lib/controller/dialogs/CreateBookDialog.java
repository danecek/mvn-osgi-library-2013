/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import java.awt.GridLayout;
import javax.swing.JLabel;
import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class CreateBookDialog extends AbstractLibraryDialog {

    ValidatedTF title;

    public CreateBookDialog() {
        super("Create Book"); // todo
        title = new ValidatedTF(this);
        content.setLayout(new GridLayout(0, 2));
        content.add(new JLabel("Title:"));
        content.add(title);
        validateDialog();
        pack();
        setVisible(true);
    }

    public boolean validateDialog() {
        if (title.getText().isEmpty()) {
            error("Prazdny titul");// todo
            return false;
        }
        clearError();
        return true;


    }

    @Override
    void okAction() {
        try {
            LibraryFacade.getDefault().createBook(title.getText());
        } catch (LibraryException le) {
            System.out.println(le);  // todo
        }
    }
}
