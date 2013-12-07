/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class GetBooks extends LibraryCommand {

    public GetBooks() {
    }

    @Override
    public Books execute(LibraryFacade libraryFacade) throws LibraryException {
        return new Books(libraryFacade.getBooks());
    }
}
