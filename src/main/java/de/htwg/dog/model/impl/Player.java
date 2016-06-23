/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Player {
    
    private List<Card> cards = new ArrayList<>();
    private int playerNumber;
    private final List<Square> homeSquares;
    private final List<Square> finishSquares;
    private List<Square> occupiedSquares;
    private final Square startSquare;
    
    public List<Card> getCards() {
        return cards;
    }

    public List<Square> getFinishSquares() {
        return finishSquares;
    }

    public List<Square> getHomeSquares() {
        return homeSquares;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Square getStartSquare() {
        return startSquare;
    }

    public Player(Square startSquare, int number) {

        playerNumber = number;
        homeSquares = new ArrayList<>();
        finishSquares = new ArrayList<>();
        occupiedSquares = new ArrayList<>();

        this.startSquare = startSquare;

        for (int i = 0; i < 4; i++) {
            homeSquares.add(new Square("H" + i + "P" + playerNumber));
            occupiedSquares.add(homeSquares.get(i));
            finishSquares.add(new Square("F" + i + "P" + playerNumber));
        }

        for (Square s : homeSquares) {
            s.setOccupation(true);
        }
    }

    public Card getCardByName(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
    }

    public List<Square> getOccupiedSquares() {
        return occupiedSquares;
    }

    public List<String> getStringCards() {
        List<String> l = new ArrayList();

        for (Card c : cards) {
            l.add(c.getName());
        }

        return l;
    }

    public List<String> getStringOccupiedSquares() {
        List<String> l = new ArrayList();

        for (Square s : occupiedSquares) {
            l.add(s.getName());
        }

        return l;
    }
}
