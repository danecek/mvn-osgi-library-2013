/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.utils.LibraryException;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class DeleteBookAction extends AbstractLibraryAction {

    public DeleteBookAction() {
        super("Delete Book", "Book"); // todo
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Book b = MainFrame.getInstance().getSelectedBook();
        if (b == null) {
            MainFrame.getInstance().showError("Neni vybrana"); // todo

        } else {
            try {
                int result = JOptionPane.showConfirmDialog(MainFrame.getInstance(), b + "?"); // todo
                if (result == JOptionPane.YES_OPTION) {
                    LibraryFacade.getDefault().deleteBook(b.getId());

                    MainFrame.getInstance().refresh();
                }
            } catch (LibraryException ex) {
                MainFrame.getInstance().showError(ex);
            }
        }
//        new DeleteBookDialog();
    }

    @Override
    public void setEnable() {
        setEnabled(MainFrame.getInstance().getSelectedBook() != null);
    }
}
