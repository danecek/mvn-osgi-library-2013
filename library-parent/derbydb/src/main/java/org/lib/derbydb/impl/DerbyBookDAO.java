/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    PreparedStatement createPS;
    private PreparedStatement findAllPS;
    private PreparedStatement deletePS;

    public DerbyBookDAO(Connection connection) {
        try {
            createPS = connection.prepareStatement("INSERT INTO BOOKS VALUES(DEFAULT, ?)");
            deletePS = connection.prepareStatement("DELETE FROM BOOKS WHERE ID = ?");
            findAllPS = connection.prepareStatement("SELECT * FROM BOOKS");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(String title) throws LibraryException {
        try {
            createPS.setString(1, title);
            createPS.execute();
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void delete(BookId id) throws LibraryException {
        try {
            deletePS.setInt(1, id.getId());
            deletePS.execute();
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void update(Book reader) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book find(BookId id) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Book> getAll() throws LibraryException {
        try {
            ResultSet rs = findAllPS.executeQuery();
            ArrayList<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(new BookId(rs.getInt(1)), rs.getString(2)));
            }
            return books;
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }
}
