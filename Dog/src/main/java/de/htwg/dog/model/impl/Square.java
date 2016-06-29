/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.ISquare;

/**
 *
 * @author kev
 */
public final class Square implements ISquare {

    private String name;
    private boolean isOccupied;
    private Type type;
    private int number;
    
    public Square(String name) {
        setName(name);
    }
    
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public void setOccupation(boolean bool) {
        isOccupied = bool;
    }

    private void setName(String name) {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Type getType() {
        return type;
    }
}
