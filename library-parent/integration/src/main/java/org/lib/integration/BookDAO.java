/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import java.util.Collection;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface BookDAO {
    
    BookId create(String title) throws LibraryException;
    void delete(BookId id) throws LibraryException;
    void update(Book reader) throws LibraryException;
    Book find(BookId id) throws LibraryException;
    Collection<Book> getAll() throws LibraryException;
    
}
