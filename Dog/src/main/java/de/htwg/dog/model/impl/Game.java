/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.ICard;
import de.htwg.dog.model.IModel;
import de.htwg.dog.model.IPlayer;
import de.htwg.dog.model.ISquare;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kev
 */
public final class Game implements IModel {

    private List<IPlayer> players = new ArrayList<>();
    private Deck deck;
    private int cardsPerHand;
    private IPlayer currentPlayer;
    private Board board;
    private String info = "";
    private IPlayer winner;

    @Override
    public String getInfo() {
        return info;
    }

    private void start() {
        board = new Board();

        players = new ArrayList<>();
        players.add(new Player(board.getSquareByName("S0"), 0));
        players.add(new Player(board.getSquareByName("S12"), 1));
        players.add(new Player(board.getSquareByName("S24"), 2));
        players.add(new Player(board.getSquareByName("S36"), 3));

        for (IPlayer player : players) {
            board.getSquares().addAll(player.getFinishSquares());
            board.getSquares().addAll(player.getHomeSquares());
        }

        deck = new Deck();
        cardsPerHand = 6;
        currentPlayer = players.get(0);
        winner = null;

        info = "Neues Spiel gestartet.";

        newRound();
    }

    void newRound() {

        if (cardsPerHand == 1) {
            cardsPerHand = 6;
        }

        if (cardsPerHand * 4 > deck.undealedCards.size()) {
            deck = new Deck();
        }

        players.stream().forEach(player -> {
            player.getCards().clear();
            for (int i = 0; i < cardsPerHand; i++) {
                Card card = deck.undealedCards.get(new Random().nextInt(deck.undealedCards.size() - 1));
                deck.undealedCards.remove(card);
                player.getCards().add(card);
            }
        });
        cardsPerHand--;
    }

    public void nextPlayer() {

        if (!checkVictoryConditions()) {
            int playerNumber = currentPlayer.getPlayerNumber();
            IPlayer nextPlayer;

            if (playerNumber == 3) {
                playerNumber = 0;
            } else {
                playerNumber++;
            }

            nextPlayer = players.get(playerNumber);

            while (nextPlayer.getCards().isEmpty()) {
                if (nextPlayer == currentPlayer) {
                    newRound();
                    nextPlayer();
                    return;
                }

                if (playerNumber == 3) {
                    playerNumber = 0;
                } else {
                    playerNumber++;
                }

                nextPlayer = players.get(playerNumber);
            }

            currentPlayer = nextPlayer;
        }
    }

    @Override
    public IPlayer getWinner() {
        return winner;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public void discardCards() {
        currentPlayer.getCards().clear();
        info = "Player " + currentPlayer.getPlayerNumber() + " hat die Karten verworfen.";
        nextPlayer();
    }

    @Override
    public ISquare getSquare(String name) {
        return board.getSquareByName(name);
    }

    @Override
    public ICard getCard(String name) {
        return currentPlayer.getCardByName(name);
    }

    @Override
    public IPlayer getPlayer(int playerNo) {
        for (IPlayer p : players) {
            if (p.getPlayerNumber() == playerNo) {
                return p;
            }
        }
        return null;
    }
    
    public boolean hasMissingParameter(String s1, String s2, String card){
        if ("".equals(card)) {
            info = "Keine Karte ausgewählt.";
            return true;
        } else if ("".equals(s1) || "".equals(s2)) {
            info = "Zu wenig Felder ausgewählt.";
            return true;
        }
        return false;
    }
    
    public boolean hasErroneousParameter(ISquare from, ISquare to, ICard card) {
       if (from == null || to == null) {
            info = "Unbekanntes Feld ausgewählt.";
            return true;
        } else if (card == null) {
            info = "Unbekannte Karte ausgewählt.";
            return true;
        }else if (!from.isOccupied()) {
            info = "Feld ohne Spielfigur gewählt";
            return true;
        }
       
        return false;
    }
            

    @Override
    public boolean doTurn(String s1, String s2, String card) {

        if (checkVictoryConditions() || hasMissingParameter(s1, s2, card))
            return false; 

        ISquare from = getSquare(s1);
        ISquare to = getSquare(s2);
        ICard selectedCard = currentPlayer.getCardByName(card);

        if(hasErroneousParameter(from, to, selectedCard))
            return false;
        
        if (!Draw.isDrawAllowed(from, to,
                ValueEnum.valueOf(selectedCard.getValue()), currentPlayer)) {
            info = "Unerlaubter Zug.";
            return false;
        }

        players.stream().forEach(player -> {
            if (player.occupiesSquare(to)) {
                
                player.getOccupiedSquares().remove(to);
                to.setOccupation(false);

                if (ValueEnum.valueOf(selectedCard.getValue()) == ValueEnum.JACK)
                    player.getOccupiedSquares().add(from);
                else 
                    sendPlayerTokenHome(player);
            }
        });

        currentPlayer.getOccupiedSquares().remove(from);
        from.setOccupation(false);

        to.setOccupation(true);
        currentPlayer.getOccupiedSquares().add(to);
        currentPlayer.getCards().remove(selectedCard);

        nextPlayer();
        info = "Der Zug war erfolgreich.";

        return true;
    }
    
    private void sendPlayerTokenHome(IPlayer player){
        
        for (ISquare homeSquare : player.getHomeSquares()) {
            if (!homeSquare.isOccupied()) {
                player.getOccupiedSquares().add(homeSquare);
            }
            homeSquare.setOccupation(true);
            break;
        }
    }

    private boolean checkVictoryConditions() {

        boolean hasWon = true;

        for (ISquare square : currentPlayer.getFinishSquares()) {
            hasWon &= square.isOccupied();
        }

        if (hasWon) {
            winner = currentPlayer;
            info = "Das Spiel ist zu Ende.";
        }

        return hasWon;
    }

    @Override
    public void startGame() {
        this.start();
    }
}
