/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.controller.impl;

import de.htwg.dog.model.impl.ModelFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class ControllerTest {
    
    Controller contr = new Controller(new ModelFactory());
    
    public ControllerTest() {
    }
    
    @Before
    public void setUp() {
        contr.startGame();
    }

    @Test
    public void testAddUpdateListener() {
        System.out.println("addUpdateListener");
        contr.addUpdateListener(null);
    }

    @Test
    public void testDiscardCard() {
        System.out.println("discardCard");
        assertEquals(contr.getCurrentPlayerNo(), 0);
        contr.discardCard();
        assertEquals(contr.getCurrentPlayerNo(), 1);
        assertEquals(contr.getInfo(), "Player 0 hat die Karten verworfen.");
    }

    @Test
    public void testGetCurrentCards() {
        System.out.println("getCurrentCards");
        assertEquals(contr.getCurrentCards().size(), 6);
    }

    @Test
    public void testGetCurrentPlayerNo() {
        System.out.println("getCurrentPlayerNo");
        assertEquals(contr.getCurrentPlayerNo(), 0);
        contr.discardCard();
        assertEquals(contr.getCurrentPlayerNo(), 1);
    }

    @Test
    public void testGetWinnerNo() {
        System.out.println("getWinnerNo");
        assertEquals(contr.getWinnerNo(), -1);
    }

    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        assertEquals(contr.getInfo(), "Neues Spiel gestartet.");
        contr.discardCard();
        contr.discardCard();
        assertEquals(contr.getInfo(), "Player 1 hat die Karten verworfen.");
        contr.doTurn("Feld gibt es nicht", "S1", "SPADE_8");
        assertEquals(contr.getInfo(), "Unbekanntes Feld ausgewählt.");
    }

    @Test
    public void testStartGame() {
        System.out.println("startGame");
        contr.discardCard();
        contr.startGame();
        assertEquals(contr.getCurrentPlayerNo(), 0);
        assertEquals(contr.getInfo(), "Neues Spiel gestartet.");
    }

    @Test
    public void testGetPlayerNos() {
        System.out.println("getPlayerNos");
        assertEquals(contr.getPlayerNos().get(0), new Integer(0));
        assertEquals(contr.getPlayerNos().get(1), new Integer(1));
        assertEquals(contr.getPlayerNos().get(2), new Integer(2));
        assertEquals(contr.getPlayerNos().get(3), new Integer(3));
    }

    @Test
    public void testGetOccupiedSquares() {
        System.out.println("getOccupiedSquares");
        assertEquals(contr.getOccupiedSquares(0).size(), 4);
    }
    
}
