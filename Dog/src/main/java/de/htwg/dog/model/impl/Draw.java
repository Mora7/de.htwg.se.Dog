/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;

/**
 *
 * @author kev
 */
public class Draw {

    private Draw() {
    }

    public static boolean isDrawAllowed(ISquare from, ISquare to, ValueEnum card, IPlayer player) {

        boolean validDraw = false;

        if (card != ValueEnum.JOKER) 
            return checkNonJokerCardDraw(from, to, card, player);
        
        for (ValueEnum value : ValueEnum.values()) {
            if (value != ValueEnum.JOKER && value != ValueEnum.JACK) {
                validDraw = checkNonJokerCardDraw(from, to, value, player);
                if(validDraw)
                    break;
            }
        }
        
        return validDraw;
    }
        
    public static boolean checkNonJokerCardDraw(ISquare from, 
            ISquare to, ValueEnum card, IPlayer player) {

        boolean validDraw = false;
        
        if (!player.occupiesSquare(from)) {
            return false;
        }

        //from home to start square
        if (from.getType() == Square.Type.HOME && 
                to == player.getStartSquare() && 
                (card == ValueEnum.ACE || card == ValueEnum.KING)) {
            validDraw = true;
        }

        //from standart to standart square
        if (from.getType() == Square.Type.STANDART
                && to.getType() == Square.Type.STANDART) {
            validDraw |= fromStandartToStandart(from, to, card.getI1());
            validDraw |= fromStandartToStandart(from, to, card.getI2());
            validDraw |= (card == ValueEnum.JACK
                    && to.isOccupied()
                    && !player.occupiesSquare(to));
        }

        //from standart to finish square
        if (from.getType() == ISquare.Type.STANDART
                && to.getType() == ISquare.Type.FINISH) {
            for (ISquare finishSquare : player.getFinishSquares()) {
                if (to.getName().equals(finishSquare.getName())) {
                    validDraw |= fromNormalToFinish(from, to, card.getI1(), player);
                    validDraw |= fromNormalToFinish(from, to, card.getI2(), player);
                }
            }
        }

        return validDraw;
    }

    public static boolean fromStandartToStandart(ISquare from, ISquare to, int valueToGo) {
        int actualValueToGo = valueToGo;
        if (valueToGo < 0) {
            actualValueToGo += 48;
        }
        int difference = Int.getDifference(from.getNumber(), to.getNumber());
        return difference == actualValueToGo;
    }

    public static boolean fromNormalToFinish(ISquare from, ISquare to, int valueToGo, IPlayer player) {
        if (from != player.getStartSquare() && valueToGo > 0) {
            int difference = Int.getDifference(from.getNumber(), player.getStartSquare().getNumber());
            return difference + to.getNumber() + 1 == valueToGo;
        }

        return false;
    }

    static class Int {

        private Int() {
        }

        public static int getDifference(int i1, int i2) {
            int diff = i2 - i1;
            if (diff < 0) {
                diff += 48;
            }
            return diff;
        }

    }
}
