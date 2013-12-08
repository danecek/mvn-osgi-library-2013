/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business.impl;

import java.util.Collection;
import org.lib.business.LibraryFacadeService;
import org.lib.integration.DAOFactoryService;
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
public class LibraryFacadeDefault extends LibraryFacadeService {

    @Override
    public void createReader(String name, Address address) throws LibraryException {
        DAOFactoryService.getDefault().getReaderDAO().create(name, address);
    }

    @Override
    public Collection<Reader> getReaders() throws LibraryException {
        return DAOFactoryService.getDefault().getReaderDAO().getAll();
    }

    @Override
    public void updateReader(Reader reader) throws LibraryException {
        DAOFactoryService.getDefault().getReaderDAO().update(reader);
    }

    @Override
    public void deleteReader(ReaderId id) throws LibraryException {
        DAOFactoryService.getDefault().getReaderDAO().delete(id);
    }

    @Override
    public BookId createBook(String title) throws LibraryException {
        return DAOFactoryService.getDefault().getBookDAO().create(title);
    }

    @Override
    public Collection<Book> getBooks() throws LibraryException {
        return DAOFactoryService.getDefault().getBookDAO().getAll();
    }

    @Override
    public void updateBook(Book book) throws LibraryException {
        DAOFactoryService.getDefault().getBookDAO().update(book);
    }

    @Override
    public void deleteBooks(Collection<BookId> bookIds) throws LibraryException {
        for (BookId id : bookIds) {
            DAOFactoryService.getDefault().getBookDAO().delete(id);
        }
    }

    @Override
    public void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        for (Book b : books) {
            DAOFactoryService.getDefault().getBorrowDAO().returnBook(id, b.getId());
        }
    }

    @Override
    public void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        for (Book b : books) {
            DAOFactoryService.getDefault().getBorrowDAO().borrowBook(id, b.getId());
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
}
}
