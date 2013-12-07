/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class BookDAOImpl implements BookDAO {

    private static int keyCount;
    Map<BookId, Book> books = new ConcurrentHashMap<>();

    public BookDAOImpl() {
        try {
            create("RUR");
            create("Maj");
        } catch (LibraryException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BookId create(String title) throws LibraryException {
        BookId id = new BookId(++keyCount);
        Book book = new Book(id, title);
        books.put(book.getId(), book);
        return id;
    }

    @Override
    public void delete(BookId id) throws LibraryException {
        books.remove(id);
    }

    @Override
    public void update(Book reader) throws LibraryException {
        books.put(reader.getId(), reader);
    }

    @Override
    public Book find(BookId id) throws LibraryException {
        return books.get(id);
    }

    @Override
    public Collection<Book> getAll() throws LibraryException {
        return new ArrayList<Book>(books.values());
    }
}
