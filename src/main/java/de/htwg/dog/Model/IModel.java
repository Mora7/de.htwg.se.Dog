/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.Model;

import de.htwg.dog.Model.impl.Card;
import de.htwg.dog.Model.impl.Player;
import de.htwg.dog.Model.impl.Square;
import java.util.List;

/**
 *
 * @author kev
 */
public interface IModel {

    public List<Player> getPlayers();
    public Player getCurrentPlayer();

    public Card getCard(String name);
    public Square getSquare(String name);
    
    //public List<Square> getOccupiedSquares(int playerNumber);

    public void discardCards();
    public boolean doTurn(String s1, String s2, String card);
    public void startGame();
    public String getInfo();
    public Player getWinner();

}
