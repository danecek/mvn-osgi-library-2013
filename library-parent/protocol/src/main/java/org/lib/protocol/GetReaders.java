/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeInterface;
import org.lib.business.LibraryFacadeService;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class GetReaders extends LibraryCommand {

    public GetReaders() {
    }

    @Override
    public Readers execute(LibraryFacadeInterface libraryFacade) throws LibraryException {
        return new Readers(libraryFacade.getReaders());
    }
}
