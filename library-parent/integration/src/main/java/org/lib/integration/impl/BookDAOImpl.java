/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.lib.integration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class BookDAOImpl implements BookDAO {

    private static int keyCount;
    Map<BookId, Book> books = new ConcurrentHashMap<BookId, Book>();

    public Book create(String title) throws LibraryException {
        Book book = new Book(new BookId(++keyCount), title);
        books.put(book.getId(), book);
        return book;
    }

    public void delete(BookId id) throws LibraryException {
        books.remove(id);
    }

    public void update(Book reader) throws LibraryException {
        books.put(reader.getId(), reader);
    }

    public Book find(BookId id) throws LibraryException {
        return books.get(id);
    }

    public Collection<Book> getAll() throws LibraryException {
        return new ArrayList<Book>(books.values());
    }
}
