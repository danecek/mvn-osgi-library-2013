/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import org.lib.business.LibraryFacadeInterface;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public abstract class LibraryCommand implements Serializable {

    public static final Ok OK = new Ok();
    public static final int PORT = 3456;

    public abstract Object execute(LibraryFacadeService libraryFacade) throws LibraryException;

    @Override
    public String toString() {
        return String.format("%s", getClass().getSimpleName());
    }
}
