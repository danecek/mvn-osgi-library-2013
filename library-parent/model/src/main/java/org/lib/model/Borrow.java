/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author danecek
 */
public class Borrow  implements Serializable {

    ReaderId readerId;
    BookId bookId;
    Date date;

    public Borrow() {
    }

    public Borrow(ReaderId readerId, BookId bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
        date = new Date();
    }
}
