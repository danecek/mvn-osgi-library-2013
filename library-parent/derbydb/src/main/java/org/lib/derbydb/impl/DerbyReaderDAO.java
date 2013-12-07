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
import org.lib.integration.ReaderDAO;
import org.lib.model.Address;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class DerbyReaderDAO implements ReaderDAO {

    private PreparedStatement createPs;
    private PreparedStatement findAllPs;
    private PreparedStatement deletePs;
    private PreparedStatement findPs;

    public DerbyReaderDAO(Connection connection) {
        try {
            deletePs = connection.prepareStatement("DELETE FROM READERS WHERE ID = ?");
            createPs = connection.prepareStatement("INSERT INTO READERS VALUES(DEFAULT, ?, ?)");
            findAllPs = connection.prepareStatement("SELECT * FROM READERS");
            findPs = connection.prepareStatement("SELECT * FROM READERS WHERE ID = ?");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyReaderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(String name, Address address) throws LibraryException {
        try {
            createPs.setString(1, name);
            createPs.setString(2, address.toString());
            createPs.execute();
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void delete(ReaderId id) throws LibraryException {
        try {
            deletePs.setInt(1, id.getId());
            deletePs.executeUpdate();
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public Reader find(ReaderId id) throws LibraryException {
        try {
            ResultSet rs = findPs.executeQuery();
            return new Reader(new ReaderId(rs.getInt(1)), rs.getString(2), new Address(rs.getString(3)));
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public Collection<Reader> getAll() throws LibraryException {
        try {
            ResultSet rs = findAllPs.executeQuery();
            ArrayList<Reader> readers = new ArrayList<>();
            while (rs.next()) {
                readers.add(new Reader(new ReaderId(rs.getInt(1)), rs.getString(2), new Address(rs.getString(3))));
            }
            return readers;
        } catch (SQLException ex) {
            throw new LibraryException(ex);
        }
    }

    @Override
    public void update(Reader reader) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
