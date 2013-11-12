/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.BookDAO;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DerbyBookDAO implements BookDAO {
    
    PreparedStatement psCreate;

    public DerbyBookDAO(Connection con) {
        try {
            psCreate = con.prepareStatement("INSERT INTO BOOKS VALUES(DEFAULT, ?)");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Book create(String title) throws LibraryException {
        try {
            psCreate.setString(1, title);
            psCreate.execute();
            return null; // todo
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    public void delete(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Book reader) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Book find(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<Book> getAll() throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
