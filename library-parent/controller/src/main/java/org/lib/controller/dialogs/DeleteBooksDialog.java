/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.lib.business.LibraryFacadeService;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;
import org.lib.view.BookModel;

/**
 *
 * @author danecek
 */
public final class DeleteBooksDialog extends AbstractLibraryDialog {

    private Collection<Book> books;

    public DeleteBooksDialog(final Collection<Book> books) {
        super(Messages.Delete_Books.cm());
        this.books = books;
        getContent().setLayout(new BorderLayout());
        JTable tab = new JTable(new BookModel(books));
        tab.setRowSelectionAllowed(false);
        tab.setPreferredScrollableViewportSize(new Dimension(300, 100));
        JScrollPane sp = new JScrollPane(tab);
        getContent().add(sp);
        pack();
        setVisible(true);
    }

    @Override
    public boolean validateDialog() {
        return true;
    }

    @Override
    public void okAction() {
        try {
            ArrayList<BookId> bookIds = new ArrayList<>();
            for (Book book : books) {
                bookIds.add(book.getId());
            }
            LibraryFacadeService.getDefault().deleteBooks(bookIds);
            MainFrame.getInstance().refresh();
        } catch (LibraryException ex) {
            MainFrame.getInstance().showError(ex);
        }
    }
}
