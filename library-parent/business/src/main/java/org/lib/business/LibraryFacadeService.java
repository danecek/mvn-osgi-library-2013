/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.business;

import java.util.Collection;
import org.lib.business.impl.LibraryFacadeDefault;
import org.lib.model.Address;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author danecek
 */
public abstract class LibraryFacadeService implements LibraryFacadeInterface {

    private static ServiceTracker st;
    private static LibraryFacadeService libraryFacade;

    public static LibraryFacadeService getDefault() {
        if (libraryFacade == null) {
            libraryFacade = (LibraryFacadeService) st.getService();
            if (libraryFacade == null) {
                libraryFacade = new LibraryFacadeDefault();
            }
        }
        return libraryFacade;

    }

    /**
     * @param aSt the st to set
     */
    public static void setSt(ServiceTracker aSt) {
        st = aSt;
    }
}
