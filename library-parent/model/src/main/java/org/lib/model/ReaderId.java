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
public class ReaderId extends AbstrId<ReaderId> {

    public ReaderId() {
    }

    public ReaderId(int id) {
        super(id);
    }
    
}
