/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.model;

/**
 *
 * @author kev
 */
public interface ISquare {
    
    public enum Type {
        STANDART, FINISH, HOME, DEFAULT
    }
    
    public boolean isOccupied();

    public void setOccupation(boolean bool);

    public void setName(String name);

    public String getName();

    public int getNumber();

    public Type getType(); 
}
