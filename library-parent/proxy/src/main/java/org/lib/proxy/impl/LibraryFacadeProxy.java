/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import java.awt.print.Book;
import java.io.Reader;
import java.sql.Connection;
import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.connection.ConnectionService;
import org.lib.model.BookId;
import org.lib.model.ReaderId;
import org.lib.protocol.Books;
import org.lib.protocol.CreateBook;


/**
 *
 * @author danecek
 */
public class LibraryFacadeProxy extends LibraryFacade {

    public LibraryFacadeProxy() {
    }

    @Override
    public void createReader(String name, Address address) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Reader> getReaders() throws LibraryException {
        Readers readers = (Readers) ConnectionService.getDefault().send(new GetReaders());
        return readers.getReaders();

    }

    @Override
    public void updateReader(Reader reader) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteReader(ReaderId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BookId createBook(String title) throws LibraryException {
        return (BookId) ConnectionService.getDefault().send(new CreateBook(title));
    }

    @Override
    public void updateBook(Book book) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Book> getBooks() throws LibraryException {
        Books books = (Books) ConnectionService.getDefault().send(new GetBooks());
        return books.getBooks();
    }

    @Override
    public void deleteBooks(Collection<BookId> bookIds) throws LibraryException {
        ConnectionService.getDefault().send(new DeleteBooks(bookIds));
    }

    @Override
    public void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
