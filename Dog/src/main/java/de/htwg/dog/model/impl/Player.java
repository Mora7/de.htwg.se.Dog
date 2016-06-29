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

        homeSquares.stream().forEach(s -> 
            s.setOccupation(true)
        );
    }
    
    @Override
    public boolean occupiesSquare(ISquare square){
        for (ISquare occupiedSquare : occupiedSquares) {
            if(occupiedSquare.getName().equals(square.getName()))
                return true;
        }
        
        return false;
    }
    
    @Override
    public List<ICard> getCards() {
        return cards;
    }

    @Override
    public List<ISquare> getFinishSquares() {
        return finishSquares;
    }

    @Override
    public List<ISquare> getHomeSquares() {
        return homeSquares;
    }

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public ISquare getStartSquare() {
        return startSquare;
    }

    @Override
    public ICard getCardByName(String name) {
        for (ICard card : cards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
    }

    @Override
    public List<ISquare> getOccupiedSquares() {
        return occupiedSquares;
    }
}
