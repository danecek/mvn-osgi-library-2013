/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.EmbeddedDriver;
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
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            Class<EmbeddedDriver> c = org.apache.derby.jdbc.EmbeddedDriver.class;
            String derbyUrl = "jdbc:derby:" + System.getProperty("user.home")
                    + System.getProperty("file.separator") + "libraryDB; create = true";
            con = DriverManager.getConnection(derbyUrl);
            DatabaseMetaData dmd = con.getMetaData();
            ResultSet rs = dmd.getTables(null, null, "BOOKS", null);
            if (rs == null) {
                Statement s = con.createStatement();
              s.executeUpdate("create table BOOKs ...");
            }
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
