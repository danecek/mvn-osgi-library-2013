/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import java.util.Collection;
import org.lib.model.BookId;
import org.lib.model.Borrow;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface BorrowDAO {

    void borrowBook(ReaderId readerId, BookId bookId) throws LibraryException;

    void returnBook(ReaderId readerId, BookId bookId) throws LibraryException;

    Collection<Borrow> borrows(ReaderId readerId) throws LibraryException;

    Borrow findReader(BookId bookId) throws LibraryException;
}
