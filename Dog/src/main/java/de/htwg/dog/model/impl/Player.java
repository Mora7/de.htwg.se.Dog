/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.ICard;
import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Player implements IPlayer {
    
    private final List<ICard> cards = new ArrayList<>();
    private final int playerNumber;
    private final List<ISquare> homeSquares;
    private final List<ISquare> finishSquares;
    private final List<ISquare> occupiedSquares;
    private final ISquare startSquare;
    
    public Player(ISquare startSquare, int number) {

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

        for (ISquare s : homeSquares) {
            s.setOccupation(true);
        }
    }
    
    public List<ICard> getCards() {
        return cards;
    }

    public List<ISquare> getFinishSquares() {
        return finishSquares;
    }

    public List<ISquare> getHomeSquares() {
        return homeSquares;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public ISquare getStartSquare() {
        return startSquare;
    }

    public ICard getCardByName(String name) {
        for (ICard card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
    }

    public List<ISquare> getOccupiedSquares() {
        return occupiedSquares;
    }
}
