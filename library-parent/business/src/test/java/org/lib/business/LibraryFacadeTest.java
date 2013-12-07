/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Arrays;
import java.util.Collection;
import org.lib.model.Address;
import org.lib.model.Reader;
import static org.junit.Assert.*;

/**
 *
 * @author danecek
 */
public class LibraryFacadeTest {

    public LibraryFacadeTest() {
    }

    @org.junit.Test
    public void testCreateReader() throws Exception {
        System.out.println("createReader");
        LibraryFacade.getDefault().createReader("Novak", new Address("Praha"));
        LibraryFacade.getDefault().createReader("Svoboda", new Address("Praha"));
        Collection<Reader> readers = LibraryFacade.getDefault().getReaders();
      //  assertTrue(readers.containsAll(Arrays.asList(novak, svoboda)));

    }
}