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
public class GameTest {
    
    public GameTest() {
    }
    
    Game game;
    
    @Before
    public void setUp() {
        game = new Game();
        game.startGame();
    }

    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        assertEquals(game.getInfo(), "Neues Spiel gestartet.");
        game.discardCards();
        game.discardCards();
        assertEquals(game.getInfo(), "Player 1 hat die Karten verworfen.");
        game.doTurn("Feld gibt es nicht", "S1", "SPADE_8");
        assertEquals(game.getInfo(), "Unbekanntes Feld ausgewählt.");
    }

    /*@Test
    public void testNewRound() {
        /*System.out.println("newRound");
        Game instance = new Game();
        instance.newRound();
        fail("The test case is a prototype.");
    }

    @Test
    public void testNextPlayer() {
        System.out.println("nextPlayer");
        Game instance = new Game();
        instance.nextPlayer();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getWinner();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        Game instance = new Game();
        Player expResult = null;
        Player result = instance.getCurrentPlayer();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        Game instance = new Game();
        List<Player> expResult = null;
        List<Player> result = instance.getPlayers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDiscardCards() {
        System.out.println("discardCards");
        Game instance = new Game();
        instance.discardCards();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSquare() {
        System.out.println("getSquare");
        String name = "";
        Game instance = new Game();
        Square expResult = null;
        Square result = instance.getSquare(name);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCard() {
        System.out.println("getCard");
        String name = "";
        Game instance = new Game();
        Card expResult = null;
        Card result = instance.getCard(name);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDoTurn() {
        System.out.println("doTurn");
        String s1 = "";
        String s2 = "";
        String card = "";
        Game instance = new Game();
        boolean expResult = false;
        boolean result = instance.doTurn(s1, s2, card);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testStartGame() {
        System.out.println("startGame");
        Game instance = new Game();
        instance.startGame();
        fail("The test case is a prototype.");
    }*/
    
}
