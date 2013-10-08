/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danecek
 */
public class AbstrIdTest {

    public AbstrIdTest() {
    }

    @Test
    public void testEquals() {
        ReaderId ri = new ReaderId(100);
        ReaderId ri2 = new ReaderId(100);
        assertTrue(ri2.equals(ri));

    }

    /**
     * Test of compareTo method, of class AbstrId.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        ReaderId ri = new ReaderId(100);
        ReaderId ri2 = new ReaderId(200);
        assertTrue(ri.compareTo(ri2) < 0) ;
    }

    /**
     * Test of getId method, of class AbstrId.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
    }
}