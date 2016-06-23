/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.controller;

import de.htwg.dog.model.impl.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Controller {

    private final Game game;

    public Controller(Game theModel) {

        this.game = theModel;
        updateListener = new ArrayList<>();
    }

    List<ActionListener> updateListener;
    
    public void addUpdateListener(ActionListener actionListener) {
        updateListener.add(actionListener);
    }
    
    private void update() {
        for (ActionListener l : this.updateListener) {
            l.actionPerformed(new ActionEvent(this, 0, "update"));
        }
    }
            
    public void discardCard(){
        game.discardCards();
        update();
    }

    public boolean doTurn(String s1, String s2, String card) {
        boolean b = game.doTurn(s1, s2, card);
        update();
        return b;
    }
    
    public List<String> getCurrentCards(){
        return game.getCurrentPlayer().getStringCards();
    }

    public int getCurrentPlayerNo(){
        return game.getCurrentPlayer().getPlayerNumber();
    }
    
    public int getWinnerNo(){
        if(game.getWinner() == null) return -1;
        else return game.getWinner().getPlayerNumber();
    }
    
    public String getInfo(){
        return game.getInfo();
    }

    public void startGame() {
        game.startGame();
        update();
    }
}
