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
import org.osgi.framework.BundleContext;

/**
 *
 * @author danecek
 */
public class DerbyDAOFactory extends AbstractDAOFactory {

    Connection con;
    DerbyBookDAO derbyBookDAO;
    DerbyReaderDAO derbyReaderDAO;
    BundleContext context;

    public DerbyDAOFactory(BundleContext context) {
        try {
            this.context = context;
            con = createConnection();
            DatabaseMetaData m = con.getMetaData();
            ResultSet rs = m.getTables(null, null, "BOOKS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table BOOKS generated");
                Statement s = con.createStatement();
                s.executeUpdate("CREATE TABLE BOOKS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "TITLE   VARCHAR(50),"
                        + "PRIMARY KEY (ID))");

            }
            rs = m.getTables(null, null, "READERS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table READERS generated");
                Statement s = con.createStatement();
                s.executeUpdate("CREATE TABLE READERS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "NAME   VARCHAR(50),"
                        + "ADDRESS   VARCHAR(150),"
                        + "PRIMARY KEY (ID))");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection createConnection() {
        Connection dbConnection = null;
        try {
            //  context.getBundle().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            // getClass().getClassLoader().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            // Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, getClass().getClassLoader());
            //    context.getBundle(null).getBundleContext().loadClass("org.apache.derby.jdbc.EmbeddedDriver");
            //   Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, context.getBundle().loadClass(null));
            // Class.forName("org.apache.derby.jdbc.EmbeddedDriver", true, Thread.currentThread().getContextClassLoader());
            new org.apache.derby.jdbc.EmbeddedDriver();
            // System.out.println(ed.getClass().getClassLoader());
            //   System.out.println(getClass().getClassLoader());
            dbConnection = DriverManager.getConnection("jdbc:derby:/home/danecek/libraryDB; create=true");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbConnection;
    }
//        Driver d = null;
//        try {
//            String derbyUrl = "jdbc:derby:" + System.getProperty("user.home")
//                    + System.getProperty("file.separator") + "libraryDB; create = true";
//            try {
//                //d = (Driver) Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//                d = (Driver) Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InstantiationException ex) {
//                Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            con = d.connect(derbyUrl, null);//DriverManager.getConnection(derbyUrl);
//        } catch (SQLException ex) {
//            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return con;
//}

    @Override
    public ReaderDAO getReaderDAO() {
        if (derbyReaderDAO == null) {
            derbyReaderDAO = new DerbyReaderDAO(con);
        }
        return derbyReaderDAO;
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
