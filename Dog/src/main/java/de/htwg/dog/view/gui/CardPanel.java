/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.view.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;

/**
 *
 * @author kev
 */
public final class CardPanel extends JPanel {

    List<ActionListener> listeners = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    private Card selectedCard;

    public CardPanel() {
        setBackground(Color.lightGray);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { 
                if (e.getButton() == 1) {
                    ListIterator iter = cards.listIterator(cards.size());
                    while (iter.hasPrevious()) {
                        Card card = (Card) iter.previous();
                        if (card.getRect().contains(e.getX(), e.getY())) {
                            selectedCard = card;
                            repaint();
                            return;
                        }
                    }
                }
            }
        });
    }
    
    public void setCards(List<String> cards) {
        this.cards = new ArrayList<>();
        cards.stream().forEach((card) -> {
            Card c = new Card(card);
            this.cards.add(c);
            if (selectedCard != null && selectedCard.value.equals(card)) {
                selectedCard = c;
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (!cards.isEmpty()) {

            int start = 10;
            int step = 0;
            if(cards.size() > 1)
            {
                step = (this.getHeight() - 2 * start - cards.get(0).getHeight()) / (cards.size() - 1);
            }
            int x = start;
            int y = start;

            for (Card card : cards) {

                card.setRect(new Rectangle2D.Double(x, y, card.getWidth(), card.getHeight()));

                if (card == selectedCard) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.BLACK);
                }
                g2d.setStroke(new BasicStroke(3));
                g2d.drawImage(card.getImage(), card.getX(), card.getY(), null);
                g2d.drawRect(card.getX(), card.getY(), card.getWidth(), card.getHeight());
                g2d.setStroke(new BasicStroke());
                y += step;

            }
        }
    }
    
    public String getSelectedCard() {
        if(selectedCard == null) 
            return "";
        else return selectedCard.getValue();
    }


    private class Card {

        private BufferedImage image = null;
        private Rectangle2D rect;
        private String value;
        
        public Card(String value) {
            this.value = value;

            this.image = Images.imageList.get(value);
            this.rect = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
        }

        public BufferedImage getImage() {
            return image;
        }

        public String getValue() {
            return value;
        }

        public int getX() {
            return (int) rect.getX();
        }

        public int getY() {
            return (int) rect.getY();
        }

        public int getWidth() {
            return (int) rect.getWidth();
        }

        public int getHeight() {
            return (int) rect.getHeight();
        }

        public void setRect(Rectangle2D rectangle) {
            rect = rectangle;
        }

        public Rectangle2D getRect() {
            return rect;
        }
    }
}
