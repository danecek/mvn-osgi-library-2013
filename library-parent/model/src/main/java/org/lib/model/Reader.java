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
public class Reader  implements Serializable  {

    public Reader() {
    }

    public Reader(ReaderId id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    private ReaderId id;
    private String name;
    private Address address;

    /**
     * @return the id
     */
    public ReaderId getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param id the id to set
     */
    public void setId(ReaderId id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s(%d: %s, %s)", getClass().getSimpleName(), id, name, address);
    }
    
}
