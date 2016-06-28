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
public interface IModel {

    public List<IPlayer> getPlayers();
    public IPlayer getPlayer(int playerNo);
    public IPlayer getCurrentPlayer();

    public ICard getCard(String name);
    public ISquare getSquare(String name);

    public void discardCards();
    public boolean doTurn(String s1, String s2, String card);
    public void startGame();
    public String getInfo();
    public IPlayer getWinner();

}
