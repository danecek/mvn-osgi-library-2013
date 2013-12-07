/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import org.lib.business.LibraryFacade;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public abstract class LibraryCommand implements Serializable {

    public static final Ok OK =  new Ok();
    public static final int PORT = 3456;

    public abstract Object execute(LibraryFacade libraryFacade) throws LibraryException;
}
