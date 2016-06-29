/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class DrawTest {

    Game game = new Game();

    public DrawTest() {
    }

    @Before
    public void setUp() {
        game.startGame();
    }

    @Test
    public void testIsDrawAllowed() {
        System.out.println("isDrawAllowed");
        ISquare from = game.getSquare("S1");
        ISquare to = game.getSquare("S5");
        ValueEnum card = ValueEnum.FOUR;
        IPlayer player = game.getPlayer(0);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), false);
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        card = ValueEnum.EIGHT;
        assertEquals(Draw.isDrawAllowed(from, to, card, player), false);
        card = ValueEnum.JOKER;
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        
        from = player.getHomeSquares().get(0);
        to = player.getStartSquare();
        card = ValueEnum.ACE;
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        
        from = game.getSquare("S44");
        to = player.getFinishSquares().get(0);
        card = ValueEnum.FIVE;
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        player.getOccupiedSquares().add(to);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), false);
        
        
        from = game.getSquare("S10");
        to = game.getSquare("S33");
        card = ValueEnum.JACK;
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), false);
        to.setOccupation(true);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        
    }
}
