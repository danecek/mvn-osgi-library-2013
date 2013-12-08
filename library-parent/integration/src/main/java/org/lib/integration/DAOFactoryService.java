/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import org.lib.integration.impl.DAOFactoryDefault;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class DAOFactoryService {

    private static DAOFactoryService instance;
    private static ServiceTracker st;

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }

    public abstract ReaderDAO getReaderDAO();

    public abstract BookDAO getBookDAO();

    public abstract BorrowDAO getBorrowDAO();

    public static DAOFactoryService getDefault() {
        if (instance == null) {
            instance = (DAOFactoryService) st.getService();
            if (instance == null) {
                instance = new DAOFactoryDefault();
            }

        }
        return instance;
    }
}
