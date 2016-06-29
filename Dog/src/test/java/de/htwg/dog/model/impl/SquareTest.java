/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.model.impl;

import de.htwg.dog.model.ISquare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class SquareTest {
    
    public SquareTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetNumber() {
        System.out.println("getName");
        Square s = new Square("Unbekanntes Feld");
        assertEquals(s.getNumber(), 0);
        assertEquals(s.getType(), Square.Type.DEFAULT);
    }   
}
