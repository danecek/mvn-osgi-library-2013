/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import org.lib.integration.AbstractDAOFactory;
import org.lib.integration.ReaderDAO;

/**
 *
 * @author danecek
 */
public class DAOFactoryDefault extends AbstractDAOFactory {

    ReaderDAODefault deaderDAODefault;

    @Override
    public ReaderDAO getReaderDAO() {
        if (deaderDAODefault == null) {
            deaderDAODefault =
                    new ReaderDAODefault();
        }
        return deaderDAODefault;
    }
}
