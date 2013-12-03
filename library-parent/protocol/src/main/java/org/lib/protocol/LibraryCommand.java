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

    public static final String OK = "OK";

    public abstract Object execute(LibraryFacade libraryFacade) throws LibraryException;
}
