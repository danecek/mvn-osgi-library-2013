/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeInterface;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class CreateBook extends LibraryCommand {

    private String title;

    public CreateBook() {
    }

    public CreateBook(String title) {
        this.title = title;
    }

    @Override
    public Object execute(LibraryFacadeInterface libraryFacade) throws LibraryException {
        libraryFacade.createBook(getTitle());
        return OK;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", getClass().getSimpleName(), title);
    }
}
