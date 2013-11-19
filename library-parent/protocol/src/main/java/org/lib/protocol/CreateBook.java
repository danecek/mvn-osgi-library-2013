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
public class CreateBook extends LibraryCommand {

    private final String title;

    public CreateBook(String title) {
        this.title = title;
    }

    @Override
    public Object execute(LibraryFacade libraryFacade) throws LibraryException {
        libraryFacade.createBook(title);
        return OK;
    }
}
