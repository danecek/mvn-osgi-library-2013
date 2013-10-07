/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danecek
 */
public class AbstrIdTest {

    public AbstrIdTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of equals method, of class AbstrId.
     */
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



    }

    /**
     * Test of getId method, of class AbstrId.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");



    }

    public class AbstrIdImpl extends AbstrId {

        public AbstrIdImpl() {
            super(0);
        }
    }
}