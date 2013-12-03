/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class GetReaders extends LibraryCommand {


    @Override
    public Object execute(LibraryFacade libraryFacade) throws LibraryException {
        return libraryFacade.getReaders();
    }
}
