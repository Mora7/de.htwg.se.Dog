/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.model;


import java.util.List;

/**
 *
 * @author kev
 */
public interface IPlayer {
    
    public List<ICard> getCards();

    public List<ISquare> getFinishSquares();

    public List<ISquare> getHomeSquares();

    public int getPlayerNumber();

    public ISquare getStartSquare();

    public ICard getCardByName(String name);

    public List<ISquare> getOccupiedSquares(); 
    
    public boolean occupiesSquare(ISquare square);
}
