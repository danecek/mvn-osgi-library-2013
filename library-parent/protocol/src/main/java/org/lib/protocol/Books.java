/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import org.lib.model.Book;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class Books implements Serializable {

    private Collection<Book> books = new ArrayList<>();

    public Books() {
    }

    public Books(Collection<Book> books) {
        this.books = books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return books;
    }
}
