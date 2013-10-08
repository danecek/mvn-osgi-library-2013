/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import org.lib.integration.impl.DefaultDAOFactory;

/**
 *
 * @author danecek
 */
public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory instance;

    public abstract ReaderDAO getReaderDAO();

    public static AbstractDAOFactory getDefault() {
        if (instance == null) {
            // vyhledavani sluzby
            instance = new DefaultDAOFactory();
        }
        return instance;

    }
}
