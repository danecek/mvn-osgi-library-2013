/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import java.awt.GridLayout;
import org.lib.utils.ValidatedTF;
import javax.swing.JLabel;
import org.lib.business.LibraryFacadeService;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class CreateBookDialog extends AbstractLibraryDialog {

    private ValidatedTF title;

    public CreateBookDialog() {
        super(Messages.Create_Book.cm());
        title = new ValidatedTF(this);
        getContent().setLayout(new GridLayout(0, 2));
        getContent().add(new JLabel(Messages.Title.cm() + ": "));
        getContent().add(title);
        pack();
        setVisible(true);
        validateDialog();
    }

    @Override
    public boolean validateDialog() {
        if (title.getText().isEmpty()) {
            error(Messages.Empty_title.cm());
            return false;
        }
        clearError();
        return true;


    }

    @Override
    public void okAction() {
        try {
            LibraryFacadeService.getDefault().createBook(title.getText());
            MainFrame.getInstance().refresh();
        } catch (LibraryException le) {
            MainFrame.getInstance().showError(le);
        }
    }
}
