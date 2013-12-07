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
public class Address implements Serializable {

    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return address;
    }
}
