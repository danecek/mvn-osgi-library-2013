package org.lib.derbydb.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.DAOFactoryService;
import org.lib.integration.BookDAO;
import org.lib.integration.BorrowDAO;
import org.lib.integration.ReaderDAO;
import org.osgi.framework.BundleContext;

/**
 *
 * @author danecek
 */
public class DerbyDAOFactory extends DAOFactoryService {

    private Connection dbConnection;
    DerbyBookDAO derbyBookDAO;
    DerbyReaderDAO derbyReaderDAO;
    BundleContext context;

    public DerbyDAOFactory(BundleContext context) {
        try {
            this.context = context;
            dbConnection = createConnection();
            dbConnection.setAutoCommit(false);
            DatabaseMetaData m = dbConnection.getMetaData();
            ResultSet rs = m.getTables(null, null, "BOOKS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table BOOKS generated");
                Statement s = dbConnection.createStatement();
                s.executeUpdate("CREATE TABLE BOOKS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "TITLE   VARCHAR(50),"
                        + "PRIMARY KEY (ID))");

            }
            rs = m.getTables(null, null, "READERS", null);
            if (!rs.next()) {
                Logger.getLogger(getClass().getName()).
                        log(Level.INFO, "Table READERS generated");
                Statement s = dbConnection.createStatement();
                s.executeUpdate("CREATE TABLE READERS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "NAME   VARCHAR(50),"
                        + "ADDRESS   VARCHAR(150),"
                        + "PRIMARY KEY (ID))");
            }
            dbConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection createConnection() {
        Connection dbCon = null;
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
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/libraryDB; create=true";
            dbCon = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbCon;
    }

    @Override
    public ReaderDAO getReaderDAO() {
        if (derbyReaderDAO == null) {
            derbyReaderDAO = new DerbyReaderDAO(getDbConnection());
        }
        return derbyReaderDAO;
    }

    @Override
    public BookDAO getBookDAO() {
        if (derbyBookDAO == null) {
            derbyBookDAO = new DerbyBookDAO(getDbConnection());
        }
        return derbyBookDAO;
    }

    @Override
    public BorrowDAO getBorrowDAO() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the dbCon
     */
    public Connection getDbConnection() {
        return dbConnection;
    }

    @Override
    public void commit() {
        try {
            dbConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollback() {
        try {
            dbConnection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
