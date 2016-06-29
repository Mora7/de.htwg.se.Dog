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
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
        card = ValueEnum.EIGHT;
        assertEquals(Draw.isDrawAllowed(from, to, card, player), false);
        card = ValueEnum.JOKER;
        assertEquals(Draw.isDrawAllowed(from, to, card, player), true);
    }

    @Test
    public void testCheckNonJokerCardDraw() {
        System.out.println("checkNonJokerCardDraw");
        ISquare from = game.getSquare("S44");
        ISquare to = game.getSquare("S46");
        ValueEnum card = ValueEnum.TWO;
        IPlayer player = game.getPlayer(0);

        assertEquals(Draw.checkNonJokerCardDraw(from, to, card, player), false);
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.checkNonJokerCardDraw(from, to, card, player), true);

        from = player.getHomeSquares().get(0);
        to = player.getStartSquare();
        card = card.ACE;
        assertEquals(Draw.checkNonJokerCardDraw(from, to, card, player), true);
    
        from = game.getSquare("S44");
        to = player.getFinishSquares().get(0);
        card = card.FIVE;
        assertEquals(Draw.checkNonJokerCardDraw(from, to, card, player), true);
    }

    @Test
    public void testFromStandartToStandart_4args() {
        System.out.println("checkNonJokerCardDraw");
        ISquare from = game.getSquare("S1");
        ISquare to = game.getSquare("S11");
        ValueEnum card = ValueEnum.TEN;
        IPlayer player = game.getPlayer(0);
        from.setOccupation(true);
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.fromStandartToStandart(from, to, card, player), true);
        card = ValueEnum.FIVE;
        assertEquals(Draw.fromStandartToStandart(from, to, card, player), false);
    }

    @Test
    public void testFromStandartToStandart_3args() {
        System.out.println("checkNonJokerCardDraw");
        ISquare from = game.getSquare("S1");
        ISquare to = game.getSquare("S10");
        assertEquals(Draw.fromStandartToStandart(from, to, 9), true);
        assertEquals(Draw.fromStandartToStandart(from, to, 10), false);
    }

    @Test
    public void testFromStandartToFinish_4args_1() {
        System.out.println("fromStandartToFinish");
        IPlayer player = game.getPlayer(0);
        ISquare from = game.getSquare("S44");
        ISquare to = player.getFinishSquares().get(0);
        ValueEnum card = ValueEnum.TEN;
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.fromStandartToFinish(from, to, card, player), false);
        card = ValueEnum.FIVE;
        assertEquals(Draw.fromStandartToFinish(from, to, card, player), true);
    }

    @Test
    public void testFromStandartToFinish_4args_2() {
        System.out.println("fromStandartToFinish");
        IPlayer player = game.getPlayer(0);
        ISquare from = game.getSquare("S44");
        ISquare to = player.getFinishSquares().get(0);
        ISquare startSquare = player.getStartSquare();
        player.getOccupiedSquares().add(from);
        assertEquals(Draw.fromStandartToFinish(from, to, 6, startSquare), false);
        assertEquals(Draw.fromStandartToFinish(from, to, 5, startSquare), true);
    }

}
