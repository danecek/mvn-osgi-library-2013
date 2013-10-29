/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

/**
 *
 * @author danecek
 */
public class Address {

    private String address;

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
