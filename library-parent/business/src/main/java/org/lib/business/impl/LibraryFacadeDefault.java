/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business.impl;

import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.integration.AbstractDAOFactory;
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
public class LibraryFacadeDefault extends LibraryFacade {

    @Override
    public Reader createReader(String name, Address address) throws LibraryException {
        return AbstractDAOFactory.getDefault().getReaderDAO().create(name, address);
    }

    @Override
    public Collection<Reader> getReaders() throws LibraryException {
        return AbstractDAOFactory.getDefault().getReaderDAO().getAll();
    }

    @Override
    public void updateReader(Reader reader) throws LibraryException {
        AbstractDAOFactory.getDefault().getReaderDAO().update(reader);
    }

    @Override
    public void deleteReader(ReaderId id) throws LibraryException {
        AbstractDAOFactory.getDefault().getReaderDAO().delete(id);
    }

    @Override
    public Book createBook(String title) throws LibraryException {
        return AbstractDAOFactory.getDefault().getBookDAO().create(title);
    }

    @Override
    public Collection<Book> getBooks() throws LibraryException {
        return AbstractDAOFactory.getDefault().getBookDAO().getAll();
    }

    @Override
    public void updateBook(Book book) throws LibraryException {
        AbstractDAOFactory.getDefault().getBookDAO().update(book);
    }

    @Override
    public void deleteBook(BookId id) throws LibraryException {
        AbstractDAOFactory.getDefault().getBookDAO().delete(id);
    }

    @Override
    public void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        for (Book b : books) {
            AbstractDAOFactory.getDefault().getBorrowDAO().returnBook(id, b.getId());
        }
    }

    @Override
    public void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        for (Book b : books) {
            AbstractDAOFactory.getDefault().getBorrowDAO().borrowBook(id, b.getId());
        }
    }
}
