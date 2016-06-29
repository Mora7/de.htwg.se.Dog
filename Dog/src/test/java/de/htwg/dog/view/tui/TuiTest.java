/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.view.tui;

import de.htwg.dog.controller.impl.Controller;
import de.htwg.dog.model.impl.ModelFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kev
 */
public class TuiTest {
    
    Tui tui = new Tui(new Controller(new ModelFactory()));
    
    public TuiTest() {
    }
    
    @Before
    public void setUp() {
        tui.processInput("neu");
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        tui.update();
    }

    @Test
    public void testSetInfo() {
        System.out.println("setInfo");
        tui.setInfo("");
        tui.setInfo("test info");
    }

    @Test
    public void testProcessInput() {
        System.out.println("processInput");
        assertEquals(tui.processInput(""), true);
        assertEquals(tui.processInput("random input"), true);
        assertEquals(tui.processInput("S1,S2,SPADE"), true);
        assertEquals(tui.processInput("v"), true);
        assertEquals(tui.processInput("start"), true);
        assertEquals(tui.processInput("exit"), false);
    }
    
}
