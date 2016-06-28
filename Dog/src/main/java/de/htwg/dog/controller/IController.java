/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.controller;

import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author kev
 */
public interface IController {
    
    public void addUpdateListener(ActionListener actionListener);     
    public void discardCard();
    public boolean doTurn(String s1, String s2, String card);
    public List<String> getCurrentCards();
    public List<Integer> getPlayerNos();
    public List<String> getOccupiedSquares(int playerNo);
    public int getCurrentPlayerNo();
    public int getWinnerNo();
    public String getInfo();
    public void startGame();   
}
