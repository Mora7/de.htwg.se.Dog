/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.ISquare;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Board {

    private final List<ISquare> squares;

    public List<ISquare> getSquares() {
        return squares;
    }
    
    public Board() {
        this.squares = new ArrayList<>();

        for (int i = 0; i < 48; i++) {
            squares.add(new Square("S" + i));
        }
    }
    
    public ISquare getSquareByName(String name) {
        for (ISquare square : squares) {
            if (square.getName().equals(name)) {
                return square;
            }
        }

        return null;
    }
}
