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
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class LibraryFacade {

    private static ServiceTracker st;
    private static LibraryFacade libraryFacade;

    public static LibraryFacade getDefault() {
        if (libraryFacade == null) {
            libraryFacade = (LibraryFacade) st.getService();
            if (libraryFacade == null) {
                libraryFacade = new LibraryFacadeDefault();
            }
        }
        return libraryFacade;

    }

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }

    public abstract void createReader(String name, Address address) throws LibraryException;

    public abstract Collection<Reader> getReaders() throws LibraryException;

    public abstract void updateReader(Reader reader) throws LibraryException;

    public abstract void deleteReader(ReaderId id) throws LibraryException;

    public abstract BookId createBook(String title) throws LibraryException;

    public abstract void updateBook(Book book) throws LibraryException;

    public abstract Collection<Book> getBooks() throws LibraryException;

    public abstract void deleteBooks(Collection<BookId> bookIds) throws LibraryException;

    public abstract void returnBooks(ReaderId id, Collection<Book> books) throws LibraryException;

    public abstract void borrowBooks(ReaderId id, Collection<Book> books) throws LibraryException;

    public abstract boolean isAvailable();
}
