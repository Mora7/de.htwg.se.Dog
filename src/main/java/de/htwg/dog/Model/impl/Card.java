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
public class Card {

    private ValueEnum value;
    private SuitEnum suit;

    public Card(ValueEnum value, SuitEnum suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(String name) {
        this.suit = SuitEnum.fromString(name.substring(0, name.indexOf("_")));
        this.value = ValueEnum.fromString(name.substring(name.indexOf("_") + 1, name.length()));
    }

    void changeCard(ValueEnum value, SuitEnum suit) {
        this.value = value;
        this.suit = suit;
    }

    String getName() {
        return suit.getName() + "_" + value.getName();
    }

    ValueEnum getValue() {
        return value;
    }

    SuitEnum getSuit() {
        return suit;
    }
}
