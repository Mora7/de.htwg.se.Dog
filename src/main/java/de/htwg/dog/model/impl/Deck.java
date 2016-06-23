/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kev
 */
public class Deck {

    public Deck() {
        GenerateDeck();
    }

    private void GenerateDeck() {

        for (SuitEnum en : SuitEnum.values()) {
            for (ValueEnum e : ValueEnum.values()) {
                deckOfCards.add(new Card(e, en));
            }
        }

        undealedCards.addAll(deckOfCards);
    }

    List<Card> deckOfCards = new ArrayList<>();
    List<Card> undealedCards = new ArrayList<>();
}
