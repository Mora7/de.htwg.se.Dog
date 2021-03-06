/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.view.gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class CardPanelTest {
    
    CardPanel cardPanel = new CardPanel();
    
    public CardPanelTest() {
    }
    
    @Before
    public void setUp() {
    }


    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        BufferedImage b;
        b = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        List<String> cards = new ArrayList<>();
        cards.add("SPADE_J");
        cardPanel.setCards(cards);
        cardPanel.paintComponent(b.getGraphics());
    }

    @Test
    public void testGetSelectedCard() {
        System.out.println("getSelectedCard");
        assertEquals(cardPanel.getSelectedCard(), "");
    }

    @Test
    public void testSetCards() {
        System.out.println("setCards");
        cardPanel.setCards(null);
        cardPanel.setCards(new ArrayList<>());
    }
    
}
