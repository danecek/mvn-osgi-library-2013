/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

/**
 *
 * @author danecek
 */
public abstract class AbstrId<T extends AbstrId<T>> implements Comparable<T> {

    private int id;

    public AbstrId(int id) {
        this.id = id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }

        return compareTo((T) o) == 0;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    public int compareTo(T t) {
        return t.getId() - id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
}
