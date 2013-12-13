/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import org.lib.business.LibraryFacadeService;
import org.lib.controller.dialogs.DeleteBooksDialog;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class DeleteBooksAction extends AbstractLibraryAction {

    public DeleteBooksAction() {
        super(Messages.Delete_Books.cm(), Messages.Book.cm()); // todo
        setEnabled(false);
    }

    @Override
    public void setEnabled() {
        setEnabled(LibraryFacadeService.getDefault().isAvailable()
                && !MainFrame.getInstance().getSelectedBooks().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Collection<Book> books = MainFrame.getInstance().getSelectedBooks();
        if (books.isEmpty()) {
            MainFrame.getInstance().showError(Messages.No_selected_book.cm());
        } else {
            new DeleteBooksDialog(books);
        }
    }
}
