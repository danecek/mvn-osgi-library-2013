/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.model.Address;
import org.lib.model.Reader;

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
        LibraryFacadeService.getDefault().createReader("Novak", new Address("Praha"));
        LibraryFacadeService.getDefault().createReader("Svoboda", new Address("Praha"));
        Collection<Reader> readers = LibraryFacadeService.getDefault().getReaders();
        //  assertTrue(readers.containsAll(Arrays.asList(novak, svoboda)));

    }
}