/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.model.impl;

import de.htwg.dog.model.IModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kev
 */
public final class Game implements IModel {

    List<Player> players;
    Deck deck;
    int cardsPerHand;
    Player currentPlayer;
    Board board;
    String info = "";
    Player winner;
    
    public Game() {
        Start();
    }
    
    @Override
    public String getInfo(){
        return info;
    }

    void Start() {
       board = new Board();
       
        players = new ArrayList<>();
        players.add(new Player(board.getSquareByName("S0"), 0));
        players.add(new Player(board.getSquareByName("S12"), 1));
        players.add(new Player(board.getSquareByName("S24"), 2));
        players.add(new Player(board.getSquareByName("S36"), 3));

        for (Player player : players) {
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

        if (cardsPerHand == 1)  
            cardsPerHand = 6;
        
        if (cardsPerHand * 4 > deck.undealedCards.size()) 
            deck = new Deck();

        for (Player player : players) {
            for (int i = 0; i < cardsPerHand; i++) {
                Card card = deck.undealedCards.get(new Random().nextInt(deck.undealedCards.size() - 1));
                deck.undealedCards.remove(card);
                player.getCards().add(card);
            }
        }
        cardsPerHand--;
    }

    public void nextPlayer() {
        
        if(!checkVictoryConditions())
        {
            int playerNumber = currentPlayer.getPlayerNumber();
            Player nextPlayer;

            if (playerNumber == 3) 
                playerNumber = 0;
            else playerNumber++;

            nextPlayer = players.get(playerNumber);

            while (nextPlayer.getCards().isEmpty()) {
                if (nextPlayer == currentPlayer) {
                    newRound();
                    nextPlayer();
                    return;
                }

                if (playerNumber == 3) 
                    playerNumber = 0;
                else 
                    playerNumber++;

                nextPlayer = players.get(playerNumber);
            }

            currentPlayer = nextPlayer;
        }
    }

    public void setOccupiedSquares() {
        for (Player player : players)
            for (Square square : player.getOccupiedSquares())
                square.setOccupation(true);
    }

    @Override
    public Player getWinner() {
        return winner;
    }
    
    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void discardCards() {
        currentPlayer.getCards().clear();
        info = "Player " + currentPlayer.getPlayerNumber() + " hat die Karten verworfen.";
        nextPlayer();
    }
    
    @Override
    public Square getSquare(String name) {
        return board.getSquareByName(name);
    }
    
    @Override
    public Card getCard(String name){
        return currentPlayer.getCardByName(name);
    }

    @Override
    public boolean doTurn(String s1, String s2, String card) {
        
        if(!checkVictoryConditions())
        {
            if("".equals(card)){ 
                info = "Keine Karte ausgewählt."; 
                return false;
            }

            if("".equals(s1) || "".equals(s2)){ 
                info = "Zu wenig Felder ausgewählt."; 
                return false;
            }

            Square from = getSquare(s1);
            Square to = getSquare(s2);
            Card selectedCard = currentPlayer.getCardByName(card);

            if(from == null || to == null){ 
                info = "Unbekanntes Feld ausgewählt."; 
                return false;
            }

            if(selectedCard == null){ 
                info = "Unbekannte Karte ausgewählt."; 
                return false;
            }

            if (from.isOccupied() && 
                    Draw.isDrawAllowed(from, to, selectedCard.getValue(), currentPlayer)) {

                    for(Player player : players){
                        for(Square occupiedSquare : player.getOccupiedSquares()){
                            if(occupiedSquare.getName().equals(to.getName())){
                                player.getOccupiedSquares().remove(to);
                                to.setOccupation(false);

                                if(selectedCard.getValue() == ValueEnum.JACK) {
                                    player.getOccupiedSquares().add(from);
                                } else {
                                    for(Square homeSquare : player.getHomeSquares()){
                                        if(!homeSquare.isOccupied())
                                            player.getOccupiedSquares().add(homeSquare);
                                            homeSquare.setOccupation(true);
                                            break;
                                    }
                                }

                            }
                        }
                    }

                    currentPlayer.getOccupiedSquares().remove(from);
                    from.setOccupation(false);

                    currentPlayer.getOccupiedSquares().add(to);
                    to.setOccupation(true);

                    currentPlayer.getCards().remove(selectedCard);

                    nextPlayer();
                    info = "Der Zug war erfolgreich.";

                    return true;
                
            }

            info = "Unerlaubter Zug.";
            return false;
        }
        
        return false;
    }

    public boolean checkVictoryConditions() {

        boolean hasWon = true;

        for (Square square : currentPlayer.getFinishSquares())
            hasWon &= square.isOccupied();

        if(hasWon) {
            winner = currentPlayer;
            info = "Das Spiel ist zu Ende.";
        }
        
        return hasWon;
    }

    @Override
    public void startGame() {
        this.Start();
    }
}
