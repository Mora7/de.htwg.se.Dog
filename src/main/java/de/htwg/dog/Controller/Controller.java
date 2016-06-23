/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.Controller;

import de.htwg.dog.Model.impl.Game;
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
    
    public void AddUpdateListener(ActionListener actionListener) {
        updateListener.add(actionListener);
    }
    
    private void update() {
        for (ActionListener l : this.updateListener) {
            l.actionPerformed(new ActionEvent(this, 0, "update"));
        }
    }
            
    public void DiscardCard(){
        game.discardCards();
        update();
    }

    public boolean DoTurn(String s1, String s2, String card) {
        boolean b = game.doTurn(s1, s2, card);
        update();
        return b;
    }
    
    public List<String> GetCurrentCards(){
        return game.getCurrentPlayer().getStringCards();
    }

    public int GetCurrentPlayerNo(){
        return game.getCurrentPlayer().playerNumber;
    }
    
    public int GetWinnerNo(){
        if(game.getWinner() == null) return -1;
        else return game.getWinner().playerNumber;
    }
    
    public String GetInfo(){
        return game.getInfo();
    }

    public void StartGame() {
        game.startGame();
        update();
    }
}
