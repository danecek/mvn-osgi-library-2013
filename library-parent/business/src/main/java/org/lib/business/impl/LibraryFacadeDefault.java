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
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public Collection<Reader> getReaders() throws LibraryException {
        return DAOFactoryService.getDefault().getReaderDAO().getAll();
    }

    @Override
    public void updateReader(Reader reader) throws LibraryException {
        DAOFactoryService.getDefault().getReaderDAO().update(reader);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void deleteReader(ReaderId id) throws LibraryException {
        DAOFactoryService.getDefault().getReaderDAO().delete(id);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void createBook(String title) throws LibraryException {
        DAOFactoryService.getDefault().getBookDAO().create(title);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public Collection<Book> getBooks() throws LibraryException {
        return DAOFactoryService.getDefault().getBookDAO().getAll();
    }

    @Override
    public void updateBook(Book book) throws LibraryException {
        DAOFactoryService.getDefault().getBookDAO().update(book);
        DAOFactoryService.getDefault().commit();
    }

    @Override
    public void deleteBooks(Collection<BookId> bookIds) throws LibraryException {
        for (BookId id : bookIds) {
            DAOFactoryService.getDefault().getBookDAO().delete(id);
        }
        DAOFactoryService.getDefault().commit();
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
