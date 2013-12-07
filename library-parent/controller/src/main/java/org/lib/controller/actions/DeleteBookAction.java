/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class DeleteBookAction extends AbstractLibraryAction {

    public DeleteBookAction() {
        super("Delete Books", "Book"); // todo
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Collection<Book> books = MainFrame.getInstance().getSelectedBooks();
        if (books.isEmpty()) {
            MainFrame.getInstance().showError("Neni vybrana"); // todo
        } else {
            try {
                int result = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "?"); // todo
                if (result == JOptionPane.YES_OPTION) {
                    ArrayList<BookId> bookIds = new ArrayList<>();
                    for (Book book : books) {
                        bookIds.add(book.getId());
                    }
                    LibraryFacade.getDefault().deleteBooks(bookIds);
                    MainFrame.getInstance().refresh();
                }
            } catch (LibraryException ex) {
                MainFrame.getInstance().showError(ex);
            }
        }
    }

    @Override
    public void setEnabled() {
        setEnabled(!MainFrame.getInstance().getSelectedBooks().isEmpty());
    }
}
