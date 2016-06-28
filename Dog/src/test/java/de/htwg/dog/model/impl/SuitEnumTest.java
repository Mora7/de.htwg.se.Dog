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
public class SuitEnumTest {
    
    public SuitEnumTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testValues() {
        System.out.println("values");
        SuitEnum[] expResult = {SuitEnum.HEART, SuitEnum.DIAMOND, 
            SuitEnum.CLUB, SuitEnum.SPADE};
        SuitEnum[] result = SuitEnum.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        assertEquals(SuitEnum.valueOf("HEART"), SuitEnum.HEART);
        assertEquals(SuitEnum.valueOf("CLUB"), SuitEnum.CLUB);
        assertEquals(SuitEnum.valueOf("DIAMOND"), SuitEnum.DIAMOND);
        assertEquals(SuitEnum.valueOf("SPADE"), SuitEnum.SPADE);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals(SuitEnum.CLUB.getName(), "CLUB");
        assertEquals(SuitEnum.DIAMOND.getName(), "DIAMOND");
        assertEquals(SuitEnum.SPADE.getName(), "SPADE");
        assertEquals(SuitEnum.HEART.getName(), "HEART");
    }

    @Test
    public void testFromString() {
        System.out.println("fromString");
        assertEquals(SuitEnum.fromString("CLUB"), SuitEnum.CLUB);
        assertEquals(SuitEnum.fromString("DIAMOND"), SuitEnum.DIAMOND);
        assertEquals(SuitEnum.fromString("SPADE"), SuitEnum.SPADE);
        assertEquals(SuitEnum.fromString("HEART"), SuitEnum.HEART);
    }
}

//was gebe ich rein, was kommt raus. Und jeweils die zu testende
//Funktion nutzen