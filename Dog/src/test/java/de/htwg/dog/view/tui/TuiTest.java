/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.view.tui;

import de.htwg.dog.controller.impl.Controller;
import de.htwg.dog.model.impl.ModelFactory;
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
        tui.processInput("");
        tui.processInput("random input");
        tui.processInput("S1,S2,SPADE_A");
        tui.processInput("v");
        tui.processInput("start");
        tui.processInput("exit");
    }
    
}
