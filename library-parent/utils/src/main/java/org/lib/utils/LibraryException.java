/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.utils;

/**
 *
 * @author danecek
 */
public class LibraryException extends Exception {

    /**
     * Creates a new instance of
     * <code>LibraryException</code> without detail message.
     */
    public LibraryException(Throwable t) {
        super(t);
    }

    /**
     * Constructs an instance of
     * <code>LibraryException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LibraryException(String msg) {
        super(msg);
    }
    
}
