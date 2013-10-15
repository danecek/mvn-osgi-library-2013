/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.business.impl.LibraryFacadeDefault;
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
public abstract class LibraryFacade {

    private static LibraryFacade libraryFacade;

    public static LibraryFacade getDefault() {
        if (libraryFacade == null) {
            // neni li jina nabidka sluzby
            libraryFacade = new LibraryFacadeDefault();
        }
        return libraryFacade;

    }

    public abstract Reader createReader(String name, Address address) throws LibraryException;

    public abstract void deleteReader(ReaderId id) throws LibraryException;

    public abstract Collection<Reader> getReaders() throws LibraryException;

    public abstract Book createBook(String title) throws LibraryException;

    public abstract void deleteBook(BookId id) throws LibraryException;

    public abstract Collection<Book> getBooks() throws LibraryException;

    public abstract void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException;

    public abstract void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException;
}
