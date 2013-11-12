/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import java.awt.GridLayout;
import javax.swing.JLabel;
import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class CreateBookDialog extends AbstractLibraryDialog {

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

    @Override
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
            MainFrame.getInstance().refresh();
        } catch (LibraryException le) {
            MainFrame.getInstance().showError(le);
        }
    }
}
