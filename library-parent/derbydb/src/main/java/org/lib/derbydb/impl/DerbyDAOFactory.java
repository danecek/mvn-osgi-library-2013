/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;
import org.lib.integration.ReaderDAO;

/**
 *
 * @author danecek
 */
public class DerbyDAOFactory extends AbstractDAOFactory {

    Connection con;
    DerbyBookDAO derbyBookDAO;

    public DerbyDAOFactory() {
        try {
            String derbyUrl = "jdbc:derby:" + System.getProperty("user.home")
                    + System.getProperty("file.separator") + "libraryDB; create = true";
            con = DriverManager.getConnection(derbyUrl);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ReaderDAO getReaderDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BookDAO getBookDAO() {
        if (derbyBookDAO == null) {
            derbyBookDAO = new DerbyBookDAO(con);
        }
        return derbyBookDAO;


    }

    @Override
    public BorrowDAO getBorrowDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
