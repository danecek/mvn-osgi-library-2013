/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class BookId extends AbstrId<BookId>{

    public BookId() {
    }

    public BookId(int id) {
        super(id);
    }
    
}
