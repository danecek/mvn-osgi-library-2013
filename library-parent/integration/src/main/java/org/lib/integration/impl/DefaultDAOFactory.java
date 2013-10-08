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
public class DefaultDAOFactory extends AbstractDAOFactory {

    private static ReaderDAO instance;

    @Override
    public ReaderDAO getReaderDAO() {
        if (instance == null) {
            instance = new ReaderDAODefault();
        }
        return instance;
    }
}
