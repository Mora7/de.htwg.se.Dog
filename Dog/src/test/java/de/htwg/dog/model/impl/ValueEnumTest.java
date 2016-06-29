/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author a1337
 */
public class ValueEnumTest {
    
    public ValueEnumTest() {
    }
    
    @Before
    public void setUp() {
    }

    /*@Test
    public void testValues() {
        System.out.println("values");
        ValueEnum[] expResult = null;
        ValueEnum[] result = ValueEnum.values();
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        ValueEnum expResult = null;
        ValueEnum result = ValueEnum.valueOf(name);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }*/

    @Test
    public void testGetI1() {
        System.out.println("getI1");
        assertEquals(ValueEnum.ACE.getI1(), 1);
    }

    @Test
    public void testGetI2() {
        System.out.println("getI2");
        assertEquals(ValueEnum.ACE.getI2(), 11);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals(ValueEnum.ACE.getName(), "A");
        assertEquals(ValueEnum.EIGHT.getName(), "8");
        assertEquals(ValueEnum.FIVE.getName(), "5");
        assertEquals(ValueEnum.FOUR.getName(), "4");
    }

    @Test
    public void testFromString() {
        System.out.println("fromString");
        assertEquals(ValueEnum.fromString("A"), ValueEnum.ACE);
        assertEquals(ValueEnum.fromString("8"), ValueEnum.EIGHT);
        assertEquals(ValueEnum.fromString("5"), ValueEnum.FIVE);
        assertEquals(ValueEnum.fromString("4"), ValueEnum.FOUR);
        assertEquals(SuitEnum.fromString("Unbekannt"), null);
    }
    
}
