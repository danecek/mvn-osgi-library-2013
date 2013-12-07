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
public class AbstrId<T extends AbstrId<T>> implements Comparable<T>, Serializable {

    private int id;

    public AbstrId() {
    }

    public AbstrId(int id) {
        this.id = id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        return compareTo((T) o) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.getId();
        return hash;
    }

    @Override
    public int compareTo(T t) {
        return getId() - t.getId();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(getId());
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
