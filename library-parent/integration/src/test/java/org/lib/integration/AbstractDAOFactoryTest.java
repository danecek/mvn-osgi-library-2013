/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lib.model.Address;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class AbstractDAOFactoryTest {
    
    public AbstractDAOFactoryTest() {
    }

    @org.junit.Test
    public void testGetDefault() {
        try {
            ReaderDAO rdao = AbstractDAOFactory.getDefault().getReaderDAO();
            rdao.create("Novak", new Address("Praha"));
            assertEquals(rdao.getAll().size(), 1);
        } catch (LibraryException ex) {
            Logger.getLogger(AbstractDAOFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}