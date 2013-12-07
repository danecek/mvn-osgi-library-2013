/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import java.util.Collection;
import org.lib.business.LibraryFacade;
import org.lib.connection.ConnectionService;
import org.lib.model.Address;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.protocol.Books;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBooks;
import org.lib.protocol.GetBooks;
import org.lib.protocol.GetReaders;
import org.lib.protocol.Readers;
import org.lib.utils.LibraryException;

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
        return ConnectionService.getDefault().<Readers>send(new GetReaders()).getReaders();
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
    public Collection<Book> getBooks() throws LibraryException {
        return ConnectionService.getDefault().<Books>send(new GetBooks()).getBooks();
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
    public void updateBook(org.lib.model.Book book) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBooks(ReaderId id, Collection<org.lib.model.Book> books) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAvailable() {        
       return ConnectionService.getDefault().isConnected();
    }
}
