/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.model.impl;

import de.htwg.dog.model.ICard;
import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class GameTest {
    
    Game game = new Game();
    
    public GameTest() {
    }
    
    @Before
    public void setUp() {
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

    @Test
    public void testNewRound() {
        System.out.println("newRound");
        assertEquals(game.currentPlayer.getCards().size(), 6);
        game.newRound();
        assertEquals(game.currentPlayer.getPlayerNumber(), 0);
        assertEquals(game.currentPlayer.getCards().size(), 5);
        game.newRound();
        game.newRound();
        game.newRound();
        assertEquals(game.currentPlayer.getCards().size(), 2);
        game.newRound();
        assertEquals(game.currentPlayer.getCards().size(), 6);
    }

    @Test
    public void testNextPlayer() {
        System.out.println("nextPlayer");
        assertEquals(game.currentPlayer.getPlayerNumber(), 0);
        game.nextPlayer();
        assertEquals(game.currentPlayer.getPlayerNumber(), 1);
    }

    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        assertEquals(game.getWinner(), null);
        IPlayer winner = game.currentPlayer;
        winner.getFinishSquares().stream().forEach(finishSquare ->
            finishSquare.setOccupation(true));
        game.doTurn("", "", "");
        assertEquals(game.getWinner(), winner);
    }

    @Test
    public void testGetCurrentPlayer() {
        System.out.println("getCurrentPlayer");
        assertEquals(game.getCurrentPlayer().getPlayerNumber(), 0);
        game.nextPlayer();
        assertEquals(game.getCurrentPlayer().getPlayerNumber(), 1);
    }

    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        assertEquals(game.players, game.getPlayers());
    }

    @Test
    public void testDiscardCards() {
        System.out.println("discardCards");
        assertEquals(game.currentPlayer.getPlayerNumber(), 0);
        game.discardCards();
        assertEquals(game.players.get(0).getCards().isEmpty(), true);
        assertEquals(game.currentPlayer.getPlayerNumber(), 1);
        assertEquals(game.getInfo(), "Player 0 hat die Karten verworfen.");
    }

    @Test
    public void testGetSquare() {
        System.out.println("getSquare");
        assertEquals(game.getSquare("Unbekanntes Feld"), null);
        assertEquals(game.getSquare("S0").getName(), "S0");
    }

    @Test
    public void testGetCard() {
        System.out.println("getCard");
        ICard card = game.currentPlayer.getCards().get(1);
        assertEquals(game.getCard(card.getName()).getName(), card.getName());
        assertEquals(game.getCard("Unbekannte Karte"), null);
    }

    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        assertEquals(game.getPlayer(0), game.currentPlayer);
        assertEquals(game.getPlayer(10), null);
    }

    @Test
    public void testHasMissingParameter() {
        System.out.println("hasMissingParameter");
        assertEquals(game.hasMissingParameter("", "", ""), true);
        assertEquals(game.hasMissingParameter("S1", "S2", "C1"), false);
        assertEquals(game.hasMissingParameter("S1", "S2", ""), true);
        assertEquals(game.hasMissingParameter("", "", "C1"), true);
        assertEquals(game.hasMissingParameter("S1", "", "C1"), true);
    }

    @Test
    public void testHasErroneousParameter() {
        System.out.println("hasErroneousParameter");
        assertEquals(game.hasErroneousParameter(null, null, null), true);
        ICard card = game.currentPlayer.getCards().get(0);
        ISquare occupiedSquare = game.currentPlayer.getOccupiedSquares().get(0);
        ISquare square = game.getSquare("S0");
        assertEquals(game.hasErroneousParameter(square, null, card), true);
        assertEquals(game.hasErroneousParameter(null, square, card), true);
        assertEquals(game.hasErroneousParameter(occupiedSquare, square, card), false);
        assertEquals(game.hasErroneousParameter(square, occupiedSquare, card), true);
        assertEquals(game.hasErroneousParameter(square, occupiedSquare, null), true);
    }
    
    

    /*@Test
    public void testDoTurn() {
        System.out.println("doTurn");
        game.doTurn("H0P0", , null)
    }*/

    @Test
    public void testStartGame() {
        System.out.println("startGame");
        game.newRound();
        game.nextPlayer();
        assertEquals(game.currentPlayer.getPlayerNumber(), 1);
        game.startGame();
        assertEquals(game.currentPlayer.getPlayerNumber(), 0);
    }
    
}
