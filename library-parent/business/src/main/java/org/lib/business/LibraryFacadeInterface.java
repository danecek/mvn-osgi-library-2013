/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.model.Address;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface LibraryFacadeInterface {

    void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException;

    void createBook(String title) throws LibraryException;

    void createReader(String name, Address address) throws LibraryException;

    void deleteBooks(Collection<BookId> bookIds) throws LibraryException;

    void deleteReader(ReaderId id) throws LibraryException;

    Collection<Book> getBooks() throws LibraryException;

    Collection<Reader> getReaders() throws LibraryException;

    boolean isAvailable();

    void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException;

    void updateBook(Book book) throws LibraryException;

    void updateReader(Reader reader) throws LibraryException;
    
}
