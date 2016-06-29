/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.view.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class BoardPanelTest {
    
    BoardPanel boardPanel = new BoardPanel();
    
    public BoardPanelTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testSetOccupiedSquares() {
        System.out.println("setOccupiedSquares");
        boardPanel.setOccupiedSquares(0, null);
        boardPanel.setOccupiedSquares(0, new ArrayList<>());
    }

    @Test
    public void testResetSelectedSquares() {
        System.out.println("resetSelectedSquares");
        boardPanel.resetSelectedSquares();
        assertEquals(boardPanel.getSelectedSquare1(), "");
        assertEquals(boardPanel.getSelectedSquare2(), "");
    }

    @Test
    public void testGetSelectedSquare1() {
        System.out.println("getSelectedSquare1");
        assertEquals(boardPanel.getSelectedSquare1(), "");
    }

    @Test
    public void testGetSelectedSquare2() {
        System.out.println("getSelectedSquare2");
        assertEquals(boardPanel.getSelectedSquare2(), "");
    }

    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        boardPanel.setSize(500, 500);
        BufferedImage b;
        b = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        boardPanel.paintComponent(b.getGraphics());
    }
}
