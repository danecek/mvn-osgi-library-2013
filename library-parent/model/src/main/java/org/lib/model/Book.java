/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;

/**
 *
 * @author danecek
 */
public class Book implements Serializable{

    private BookId id;
    private String title;

    public Book(BookId id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * @return the id
     */
    public BookId getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
}
