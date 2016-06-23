/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.View.Gui;

import de.htwg.dog.Controller.Controller;
import de.htwg.dog.Model.impl.Game;
import de.htwg.dog.Model.impl.Player;
import de.htwg.dog.View.I_UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author kev
 */
public final class Gui extends JFrame implements I_UI {

    JSplitPane splitPaneV;
    JSplitPane splitPaneH;
    BoardPanel boardPanel;
    CardPanel cardPanel;
    JPanel controlPanel;
    JTextArea commandTextArea;
    JButton executeButton;
    JButton discardButton;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem startMenuItem;
    JLabel label;
    JTextField infoBox;

    Controller contr;
    Game game;
    
    public Gui(Controller contr, Game model) {

        this.contr=contr;
        this.game=model;
        
        contr.AddUpdateListener((ActionEvent e) -> {
            update();
        });
        
        setTitle("Dog");

        createGui();
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createGui(){
        
        this.getContentPane().removeAll();
        
        ImageIcon img = new ImageIcon("Images/tailchase.png");
        this.setIconImage(img.getImage());
        
        commandTextArea = new JTextArea();
        executeButton = new JButton("Zug ausführen");
        discardButton = new JButton("Karten verwerfen");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        startMenuItem = new JMenuItem("Start");
        label = new JLabel("");
        infoBox = new JTextField("");
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        topPanel.setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        getContentPane().add(topPanel);
        menuBar.add(menu);
        menu.add(startMenuItem);
        startMenuItem.addActionListener((ActionEvent e) -> {
            commandTextArea.setText("Wählen sie eine Karte,\n"
                    + "ein Startfeld und ein Zielfeld\n"
                    + "oder verwerfen sie ihre Karten!");
            contr.StartGame();
        });

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        boardPanel = new BoardPanel();
        boardPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        boardPanel.setPreferredSize(new Dimension(500, 500));
        boardPanel.setMinimumSize(new Dimension(200, 200));

        cardPanel = new CardPanel();
        cardPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        cardPanel.setMinimumSize(new Dimension(200, 200));
        cardPanel.setPreferredSize(new Dimension(200, 300));

        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        JPanel panel2 = new JPanel(new BorderLayout(0, 5));
        panel2.setBackground(Color.lightGray);
        panel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));

        panel2.add(label, BorderLayout.CENTER);
        buttonPanel.add(discardButton);
        buttonPanel.add(executeButton);
        buttonPanel.add(infoBox);
        infoBox.setEditable(false);
        
        label.setFont(new Font("Helvetica", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        discardButton.setBorder(raisedbevel);
        discardButton.addActionListener((ActionEvent e) -> {
            contr.DiscardCard();
        });
        executeButton.setBorder(raisedbevel);
        executeButton.addActionListener((ActionEvent e) -> {
            if(contr.DoTurn(boardPanel.getSelectedSquare1(),
                            boardPanel.getSelectedSquare2(),
                            cardPanel.getSelectedCard()))
            {
                boardPanel.resetSelectedSquares();
            }
        });
        JScrollPane scrollPane = new JScrollPane(commandTextArea);
        scrollPane.setBorder(loweredbevel);
        panel2.add(buttonPanel, BorderLayout.PAGE_END);
        panel.add(panel2, BorderLayout.PAGE_START);
        panel.add(scrollPane, BorderLayout.CENTER);
        commandTextArea.setLineWrap(true);
        commandTextArea.setEditable(false);
        controlPanel.setBackground(Color.lightGray);
        panel.setBackground(Color.lightGray);
        controlPanel.add(panel, BorderLayout.CENTER);

        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setBorder(null);
        BasicSplitPaneUI ui = (BasicSplitPaneUI) splitPaneH.getUI();
        BasicSplitPaneDivider divider = ui.getDivider();
        divider.setBorder(new EmptyBorder(new Insets(2, 2, 2, 2)));
        topPanel.add(splitPaneH, BorderLayout.CENTER);

        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneV.setBorder(null);

        ui = (BasicSplitPaneUI) splitPaneV.getUI();
        divider = ui.getDivider();
        divider.setBorder(new EmptyBorder(new Insets(2, 2, 2, 2)));

        splitPaneV.setMinimumSize(new Dimension(175, 600));
        splitPaneV.setLeftComponent(cardPanel);
        splitPaneV.setRightComponent(controlPanel);

        splitPaneH.setLeftComponent(splitPaneV);
        splitPaneH.setRightComponent(boardPanel);

        setMinimumSize(new Dimension(500, 500));
        pack();
    }

    @Override
    public void update() {
        List<String> currentCards = contr.GetCurrentCards();
        Collections.sort(currentCards);
        cardPanel.setCards(currentCards);
        
        infoBox.setText(contr.GetInfo());
        
        String currentPlayer = "Player " + contr.GetCurrentPlayerNo();
        label.setText(currentPlayer);
        
        for (Player player : game.getPlayers()) {
            boardPanel.getBoard().getPlayers().get(player.getPlayerNumber())
                    .setOccupiedSquares(player.getStringOccupiedSquares());
        }
        
        if(contr.GetWinnerNo() >= 0){
            commandTextArea.setText("Player " + contr.GetWinnerNo() + " hat gewonnen!\n"
                    + "Herzlichen Glückwunsch!\n"
                    + "Starten sie ein neues Spiel.");    
        }
        
        repaint();
    }

}
