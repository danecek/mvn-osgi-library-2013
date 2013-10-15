/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;
import org.lib.integration.ReaderDAO;

/**
 *
 * @author danecek
 */
public class DAOFactoryDefault extends AbstractDAOFactory {

    private ReaderDAO readerDAO;
    private BookDAO bookDAO;
    private BorrowDAO borrowDAO;

    @Override
    public ReaderDAO getReaderDAO() {
        if (readerDAO == null) {
            readerDAO =
                    new ReaderDAOImpl();
        }
        return readerDAO;
    }

    @Override
    public BookDAO getBookDAO() {
        if (bookDAO == null) {
            bookDAO =
                    new BookDAOImpl();
        }
        return bookDAO;
    }

    @Override
    public BorrowDAO getBorrowDAO() {
        if (borrowDAO == null) {
            borrowDAO =
                    new BorrowDAOImpl();
        }
        return borrowDAO;
    }
}
