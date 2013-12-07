/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacade;
import org.lib.model.Reader;
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
    public Collection<Reader> execute(LibraryFacade libraryFacade) throws LibraryException {
        return libraryFacade.getReaders();
    }
}
