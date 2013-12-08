/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeService;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class Disconnect extends LibraryCommand {

    public Disconnect() {
    }

    @Override
    public Object execute(LibraryFacadeService libraryFacade) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
