/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.lib.integration.BorrowDAO;
import org.lib.model.BookId;
import org.lib.model.Borrow;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class BorrowDAOImpl implements BorrowDAO {
    
    Map<BookId, Borrow> bookToBorrow;
    Map<ReaderId, Collection<Borrow>> readerToBorrows; 

    public void borrowBook(ReaderId readerId, BookId bookId) throws LibraryException {
        Borrow brw = new Borrow(readerId, bookId);
        bookToBorrow.put(bookId, brw);
        Collection<Borrow> brws = readerToBorrows.get(readerId);
        if (brws == null) {
            brws = new HashSet<Borrow>();
            readerToBorrows.put(readerId, brws);
        }
        brws.add(brw);
    }

    public void returnBook(ReaderId readerId, BookId bookId) throws LibraryException {
        Borrow brw = bookToBorrow.remove(bookId);
        readerToBorrows.get(readerId).remove(brw);      
    }

    public Collection<Borrow> borrows(ReaderId readerId) throws LibraryException {
        return readerToBorrows.get(readerId);
   }

    public Borrow findReader(BookId bookId) throws LibraryException {
       return bookToBorrow.get(bookId);
    }
    
}
