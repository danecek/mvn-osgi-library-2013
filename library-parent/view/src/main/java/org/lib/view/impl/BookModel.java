/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.utils.LibraryException;
import static org.lib.utils.Messages.*;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class BookModel extends AbstractTableModel implements Refreshable {

    List<Book> books = new ArrayList<>();

    public BookModel() {
//        try {
//            refresh();
            MainFrame.addRefreshable(this);
//        } catch (LibraryException ex) {
//            Logger.getLogger(BookModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    Collection<Book> getBooks(int[] rows) {
        ArrayList<Book> sbs = new ArrayList<>();
        for (int row : rows) {
            sbs.add(books.get(row));
        }
        return sbs;
    }

    @Override
    public void refresh() throws LibraryException {
        Collection<Book> rs = LibraryFacade.getDefault().getBooks();
        books = new ArrayList<>(rs);
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Collator.getInstance(new Locale("cz")).compare(b1.getTitle(), b2.getTitle());
            }
        });
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();

    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Book r = books.get(row);
        switch (col) {
            case 0:
                return r.getId();
            case 1:
                return r.getTitle();
            default:
                assert false;
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return Id.cm();
            case 1:
                return Title.cm();
            default:
                assert false;
                return null;
        }
    }
}
