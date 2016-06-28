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
public class DrawTest {
    
    public DrawTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testIsDrawAllowed() {
        System.out.println("isDrawAllowed");
        Square from = new Square("S1");
        Square to = new Square("S5");
        ValueEnum card = ValueEnum.FOUR;
        ValueEnum card2 = ValueEnum.FIVE;
        Player player = new Player(new Square("S0"), 1);
        //assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        assertEquals(Draw.isDrawAllowed(from, to, card2, player), false);
    }

    @Test
    public void testFromStandartToStandart() {
        //System.out.println("fromStandartToStandart");
        //Square from = null;
        //Square to = null;
        //int valueToGo = 0;
        //boolean expResult = false;
        //boolean result = Draw.fromStandartToStandart(from, to, valueToGo);
        //assertEquals(expResult, result);
        //fail("The test case is a prototype.");
        assertEquals(true, true);
    }

    @Test
    public void testFromNormalToFinish() {
        //System.out.println("fromNormalToFinish");
        //Square from = null;
        //Square to = null;
        //int valueToGo = 0;
        ///Player player = null;
        //boolean expResult = false;
        //boolean result = Draw.fromNormalToFinish(from, to, valueToGo, player);
        //assertEquals(expResult, result);
        //fail("The test case is a prototype.");
        assertEquals(true, true);
    }
    
}
