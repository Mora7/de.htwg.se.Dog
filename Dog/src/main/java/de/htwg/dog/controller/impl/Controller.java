/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.dog.controller.IController;
import de.htwg.dog.model.ICard;
import de.htwg.dog.model.IModel;
import de.htwg.dog.model.IModelFactory;
import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
@Singleton
public class Controller implements IController {

    private final IModel model;
    List<ActionListener> updateListener;

    @Inject
    public Controller(IModelFactory modelFactory) {

        this.model = modelFactory.create();
        updateListener = new ArrayList<>();
    }
    
    @Override
    public void addUpdateListener(ActionListener actionListener) {
        updateListener.add(actionListener);
    }
    
    private void update() {
        for (ActionListener l : this.updateListener) {
            l.actionPerformed(new ActionEvent(this, 0, "update"));
        }
    }
            
    @Override
    public void discardCard(){
        model.discardCards();
        update();
    }

    @Override
    public boolean doTurn(String s1, String s2, String card) {
        boolean b = model.doTurn(s1, s2, card);
        update();
        return b;
    }
    
    @Override
    public List<String> getCurrentCards(){
        List<String> cards = new ArrayList();
        for (ICard c : model.getCurrentPlayer().getCards()) {
            cards.add(c.getName());
        }

        return cards;
    }

    @Override
    public int getCurrentPlayerNo(){
        return model.getCurrentPlayer().getPlayerNumber();
    }
    
    @Override
    public int getWinnerNo(){
        if(model.getWinner() == null) 
            return -1;
        else 
            return model.getWinner().getPlayerNumber();
    }
    
    @Override
    public String getInfo(){
        return model.getInfo();
    }

    @Override
    public void startGame() {
        model.startGame();
        update();
    }

    @Override
    public List<Integer> getPlayerNos() {
        List<Integer> numbers = new ArrayList<>();
        for(IPlayer p : model.getPlayers()) {
            numbers.add(p.getPlayerNumber());
        }
        
        return numbers;
    }

    @Override
    public List<String> getOccupiedSquares(int playerNo) {
        List<String> squares = new ArrayList();
        for (ISquare s : model.getPlayer(playerNo).getOccupiedSquares()) {
            squares.add(s.getName());
        }

        return squares;
    }
}
