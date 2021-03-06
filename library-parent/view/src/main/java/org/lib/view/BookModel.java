/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import org.lib.business.LibraryFacadeService;
import org.lib.model.Book;
import org.lib.utils.LibraryException;
import org.lib.view.impl.Refreshable;
import static org.lib.utils.Messages.*;

/**
 *
 * @author danecek
 */
public final class BookModel extends AbstractTableModel implements Refreshable {

    private List<Book> books;

    public BookModel(Collection<Book> books) {
        this.books = new ArrayList<>(books);
    }

    public BookModel() {
        this(new ArrayList<Book>());
    }
    

    public Collection<Book> getBooks(int[] rows) {
        ArrayList<Book> sbs = new ArrayList<>();
        for (int row : rows) {
            sbs.add(books.get(row));
        }
        return sbs;
    }

    @Override
    public void refresh() throws LibraryException {
        Collection<Book> rs = LibraryFacadeService.getDefault().getBooks();
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
