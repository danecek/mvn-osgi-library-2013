/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import org.lib.model.Reader;

/**
 *
 * @author danecek
 */
@XmlRootElement
public class Readers implements Serializable {

    private Collection<Reader> readers = new ArrayList<>();

    public Readers() {
    }

    public Readers(Collection<Reader> readers) {
        this.readers = readers;
    }

    /**
     * @return the readers
     */
    public Collection<Reader> getReaders() {
        return readers;
    }

    /**
     * @param readers the readers to set
     */
    public void setReaders(Collection<Reader> readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return readers.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
