/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.Model.impl;

/**
 *
 * @author kev
 */
public class Draw {

    public static boolean isDrawAllowed(Square from, Square to, ValueEnum card, Player player) {

        boolean validDraw = false;
        
        if(card == ValueEnum.JOKER) {
            for(ValueEnum value : ValueEnum.values()) {
                if(value != ValueEnum.JOKER && value != ValueEnum.JACK) { 
                    validDraw = isDrawAllowed(from, to, value, player);
                    if(validDraw) return validDraw;
                }
            }
        }

        for (Square square : player.occupiedSquares) {
            
            // square has token from current player
            if (from.getName().equals(square.getName())) {

                //from home to start square
                if (from.getType() == Square.Type.HOME && to == player.startSquare) {
                    if (card == ValueEnum.ACE || card == ValueEnum.KING) {
                        return true;
                    }
                }

                //from standart to standart square
                if (from.getType() == Square.Type.STANDART && to.getType() == Square.Type.STANDART) {
                    validDraw |= fromStandartToStandart(from, to, card.getI1());
                    validDraw |= fromStandartToStandart(from, to, card.getI2());
                    validDraw |= (card == ValueEnum.JACK) 
                                    && to.isOccupied() 
                                    && !player.occupiedSquares.contains(to);
                    
                    return validDraw;
                }

                //from standart to finish square
                if (from.getType() == Square.Type.STANDART && to.getType() == Square.Type.FINISH) {
                    for(Square finishSquare : player.finishSquares){
                        if(to.getName().equals(finishSquare.getName())){
                            validDraw |= fromNormalToFinish(from,to,card.getI1(),player);
                            validDraw |= fromNormalToFinish(from,to,card.getI2(),player);
                        }
                    }
                } 
            }
        }

        return validDraw;
    }
    
    public static boolean fromStandartToStandart(Square from, Square to, int valueToGo) {
        if(valueToGo < 0) valueToGo+=48;
        int difference = Int.getDifference(from.getNumber(), to.getNumber());
        return difference == valueToGo;
    }

    public static boolean fromNormalToFinish(Square from, Square to, int valueToGo, Player player) {
        if (from != player.startSquare && valueToGo > 0) {
            int difference = Int.getDifference(from.getNumber(), player.startSquare.getNumber());
            return difference + to.getNumber() + 1 == valueToGo;
        }

        return false;
    }


    static class Int {

        public static int getDifference(int i1, int i2) {
            int diff = i2 - i1;
            if (diff < 0) {
                diff += 48;
            }
            return diff;
        }

    }
}
