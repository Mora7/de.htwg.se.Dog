/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

/**
 *
 * @author kev
 */
public final class Square {

    public enum Type {

        STANDART, FINISH, HOME, DEFAULT
    }

    private String name;
    private boolean isOccupied;
    private Type type;
    private int number;
    
    public Square(String name) {
        setName(name);
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupation(boolean bool) {
        isOccupied = bool;
    }

    public void setName(String name) {
        this.name = name;

        switch (name.charAt(0)) {
            case 'S':
                type = Type.STANDART;
                number = Integer.parseInt(name.substring(1));
                break;
            case 'F':
                type = Type.FINISH;
                number = Character.getNumericValue(name.charAt(1));
                break;
            case 'H':
                type = Type.HOME;
                number = Character.getNumericValue(name.charAt(1));
                break;
            default:
                type = Type.DEFAULT;
                number = 0;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }
}
