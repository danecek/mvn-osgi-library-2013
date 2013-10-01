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
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
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
