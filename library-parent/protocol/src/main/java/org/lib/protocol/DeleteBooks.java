/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeInterface;
import org.lib.model.BookId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class DeleteBooks extends LibraryCommand {

    private Collection<BookId> bookIds;

    public DeleteBooks() {
    }

    public DeleteBooks(Collection<BookId> bookIds) {
        this.bookIds = bookIds;
    }

    @Override
    public Object execute(LibraryFacadeInterface libraryFacade) throws LibraryException {
        libraryFacade.deleteBooks(getBookIds());
        return OK;
    }

    /**
     * @return the bookIds
     */
    public Collection<BookId> getBookIds() {
        return bookIds;
    }

    /**
     * @param bookIds the bookIds to set
     */
    public void setBookIds(Collection<BookId> bookIds) {
        this.bookIds = bookIds;
    }
    
        @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), bookIds.toString());
    }
}
