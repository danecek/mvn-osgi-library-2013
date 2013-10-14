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
        System.out.println("equals");
        
        AbstrId instance = new ReaderId(10);
        AbstrId instance2 = new ReaderId(10);
        AbstrId instance3 = new ReaderId(100);
       assertTrue(instance.equals(instance2));
       assertFalse(instance.equals(instance3));
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(new Object()));
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
    }

    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
    }

    @Test
    public void testGetId() {
        System.out.println("getId");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
    }
}