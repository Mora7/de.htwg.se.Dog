/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.View.Tui;

import de.htwg.dog.Controller.Controller;
import de.htwg.dog.Model.impl.Player;
import de.htwg.dog.Model.impl.Game;
import de.htwg.dog.View.I_UI;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author kev
 */
public class Tui implements I_UI {

    private static final Logger LOGGER = Logger.getLogger("de.htwg.dog.View.Tui.Tui");
    
        private Controller contr;
    private Game game;
    private String currentPlayer;
    private List<String> cards;
    private String info = "";

    public Tui(Controller controller, Game model) {
        this.currentPlayer = "";
        this.cards = new ArrayList<>();
        this.cards.add(" ");

        this.contr = controller;
        this.game = model;

        controller.AddUpdateListener((ActionEvent e) -> update());
    }
    
    public void paintBoard() {


        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) { 
            LOGGER.info(ex.getMessage()); 
        }

        List<String> squares = new ArrayList<>();

        Pattern p = Pattern.compile("[FSH][0-9][0-9]?[P]?[0-3]?");
        Matcher m = p.matcher(BoardLayout.get());
        while (m.find()) {
            squares.add(m.group());
        }

        Map<String, String> mapOccupiedSquares = new HashMap<>();

        for (Player player : game.getPlayers()) {
            for (String occupiedSquare : player.getStringOccupiedSquares()) {
                mapOccupiedSquares.put(occupiedSquare, "P" + player.getPlayerNumber());
            }
        }

        List<String> tokens = new ArrayList<>();

        for (String square : squares) {
            String token = mapOccupiedSquares.get(square);

            if (token == null) {
                tokens.add("");
            } else {
                tokens.add(token);
            }
        }

        System.out.printf(BoardLayout.get(), tokens.toArray());
    }
    
    @Override
    public void update() {
        List<String> currentCards = contr.GetCurrentCards();
        Collections.sort(currentCards);
        this.info = contr.GetInfo();
        
        currentPlayer = "Player " + contr.GetCurrentPlayerNo();
        this.cards.clear();
        this.cards.addAll(currentCards);

        printTUI();
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean ProcessInput(String line) {

        if ("neu".equals(line)) {
            contr.StartGame();
            return true;
        }
        
        if ("v".equals(line)) {
            contr.DiscardCard();
            return true;
        }

        if ("exit".equals(line)) {
            return false;
        }

        if (",".contains(line)) {

            String[] parts = line.split(",");
            if (parts.length == 3) {
                String selectedCard = parts[0];
                String selectedSquare1 = parts[1];
                String selectedSquare2 = parts[2];


                contr.DoTurn(selectedSquare1, selectedSquare2, selectedCard);
                
                return true;
            }
        }
        
        info = "Unbekannter Befehl";
        printTUI();
        
        return true;
    }
    
    public void printTUI() {
        paintBoard();
        System.out.println("Player: " + currentPlayer);
        System.out.println("Karten: " + cards);
        System.out.println(info + "\n");
        System.out.print("Wählen Sie, getrennt mit einem Komma, eine Karte, "
                + "ein Startfeld und ein Zielfeld (Bsp. \"SPADE_4,S1,S5\"), \n"
                + "Karten verwerfen = \"v\", Neues Spiel = \"neu\", Spiel beenden = \"exit\": ");}

}
