/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

/**
 *
 * @author danecek
 */
public class AbstrId<T extends AbstrId<T>> implements Comparable<T> {

    private final int id;

    public AbstrId(int id) {
        this.id = id;
    }

    @Override
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
        hash = 53 * hash + this.id;
        return hash;
    }

    public int compareTo(T t) {
        return id - t.getId();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
