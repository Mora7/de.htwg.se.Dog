/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.dog.View.Gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author kev
 */
public class BoardPanel extends JPanel implements MouseListener {

    List<ActionListener> listeners = new ArrayList<>();
    private Square selectedSquare1;
    private Square selectedSquare2;
    public final Board board = new Board();
    public final double scale;

    public BoardPanel() {

        setBackground(Color.lightGray);
        addMouseListener(this);
        setDoubleBuffered(true);

        scale = this.getWidth() / 373;
    }

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    private void drawSquareBorder(Graphics2D g, Square square, int thickness) {
        if (square == selectedSquare1) {
            g.setColor(StyleParameter.colorSelectedS1);
        } else if (square == selectedSquare2) {
            g.setColor(StyleParameter.colorSelectedS2);
        } else {
            g.setColor(StyleParameter.colorBorder);
        }

        g.setStroke(new BasicStroke(thickness));
        g.draw(square);
        g.setStroke(new BasicStroke(0));
    }

    private void drawSquare(Graphics2D g, Square square, Color color) {
        g.setColor(color);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
        g.fill(square);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private void drawToken(Graphics2D g, Square square, Color color, int thickness) {

        drawSquare(g, square, color);

        g.setColor(StyleParameter.colorBorder);

        g.setStroke(new BasicStroke(thickness));
        Ellipse2D ell = new Ellipse2D.Double();
        ell.setFrame(square.getCenterX() - square.getWidth() / 10, square.getCenterY() - square.getWidth() / 10, square.getWidth() / 5, square.getWidth() / 5);
        g.draw(ell);
        g.setStroke(new BasicStroke(0));

    }

    private void drawBackgorundImage(Graphics2D g2d, Point center, int drawAreaSide) {
        File file = new File("Images/tailchase.png");

        try {
            BufferedImage image = ImageIO.read(file);
            image = Images.scale(image, BufferedImage.TYPE_INT_ARGB, drawAreaSide, drawAreaSide);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
            g2d.drawImage(image, center.x - image.getWidth() / 2, center.y - image.getHeight() / 2, this);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } catch (IOException ex) {
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        double angle = Math.toRadians(270);
        double step = 2 * Math.PI / 48;
        double x, y;
        int shortPanelSide = Math.min(getWidth(), getHeight());
        int drawAreaSide = shortPanelSide - shortPanelSide / 5;
        Point center = new Point(shortPanelSide / 2, shortPanelSide - shortPanelSide / 2);
        double radius = drawAreaSide / 2;
        double radiusOfSquare = radius / 9;
        int thickness = (int) Math.round(radiusOfSquare / 6);

        drawBackgorundImage(g2d, center, drawAreaSide);

        for (Square square : board.squares) {

            x = center.x + radius * Math.cos(angle);
            y = center.y + radius * Math.sin(angle);

            square.setFrame(x - radiusOfSquare / 2, y - radiusOfSquare / 2, radiusOfSquare, radiusOfSquare);
            drawSquare(g2d, square, Color.white);
            drawSquareBorder(g2d, square, thickness);

            angle += step;
        }

        step = step * 12;

        for (Board.Player player : board.players) {

            radius = drawAreaSide / 2;

            for (Square finishSquare : player.finishSquares) {

                radius -= 1.3 * radiusOfSquare;

                x = center.x + radius * Math.cos(angle);
                y = center.y + radius * Math.sin(angle);

                finishSquare.setFrame(x - radiusOfSquare / 2, y - radiusOfSquare / 2, radiusOfSquare, radiusOfSquare);
                drawSquare(g2d, finishSquare, player.playerColor);
                drawSquareBorder(g2d, finishSquare, thickness);

            }

            radius = drawAreaSide / 2 + 1.3 * radiusOfSquare;
            double tmpAngle = angle;

            for (Square homeSquare : player.homeSquares) {

                x = center.x + radius * Math.cos(tmpAngle);
                y = center.y + radius * Math.sin(tmpAngle);

                homeSquare.setFrame(x - radiusOfSquare / 2, y - radiusOfSquare / 2, radiusOfSquare, radiusOfSquare);
                drawSquare(g2d, homeSquare, player.playerColor);
                drawSquareBorder(g2d, homeSquare, thickness);

                tmpAngle -= step / 8;
            }

            List<Square> tmpList = new ArrayList<>();
            tmpList.addAll(board.squares);
            tmpList.addAll(player.homeSquares);
            tmpList.addAll(player.finishSquares);

            for (String token : player.getOccupiedSquares()) {
                for (Square square : tmpList) {
                    if (square.name.equals(token)) {
                        drawToken(g2d, square, player.playerColor, thickness);
                        drawSquareBorder(g2d, square, thickness);
                    }
                }
            }

            angle += step;
        }
    }

    public void checkIfSquareIsClicked(MouseEvent e, List<Square> squareList) {
        for (Square square : squareList) {
            if (square.contains(e.getX(), e.getY())) {
                if (e.getButton() == 1) {
                    SquareClicked(square);
                }
            }
        }
    }
    
    public void resetSelectedSquares(){
        selectedSquare1=null;
        selectedSquare2=null;
    }
    
    public String getSelectedSquare1() {
        if(selectedSquare1 == null) return "";
        else return selectedSquare1.getName();
    }
    
    public String getSelectedSquare2() {
        if(selectedSquare2 == null) return "";
        else return selectedSquare2.getName();
    }

    public Square getSquareByName(String squareName) {
        for (Square s : board.squares) {
            if (s.name.equals(squareName)) {
                return s;
            }
        }
        for (Board.Player player : board.players) {

            for (Square s : player.finishSquares) {
                if (s.name.equals(squareName)) {
                    return s;
                }
            }
            for (Square s : player.homeSquares) {
                if (s.name.equals(squareName)) {
                    return s;
                }
            }
        }
        return null;
    }


    public void SquareClicked(Square clickedSquare) {
        if (selectedSquare1 == null && clickedSquare != selectedSquare2) {
            selectedSquare1 = clickedSquare;
        } else if (selectedSquare1 == clickedSquare) {
            selectedSquare1 = null;
        } else if (selectedSquare2 == null && clickedSquare != selectedSquare1) {
            selectedSquare2 = clickedSquare;
        } else if (selectedSquare2 == clickedSquare) {
            selectedSquare2 = null;
        }
        
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == 1) {

            checkIfSquareIsClicked(e, board.squares);

            for (Board.Player player : board.players) {

                checkIfSquareIsClicked(e, player.finishSquares);
                checkIfSquareIsClicked(e, player.homeSquares);
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private class Square extends Ellipse2D.Double {

        public String name;

        public String getName() {
            return name;
        }

        public Square(String name) {
            this.name = name;
        }

    }

    public class Board {

        public Board() {

            for (int i = 0; i < 48; i++) {
                squares.add(new Square("S" + i));
            }

            players.add(new Player(StyleParameter.colorP0, "S0", 0));
            players.add(new Player(StyleParameter.colorP1, "S12", 1));
            players.add(new Player(StyleParameter.colorP2, "S24", 2));
            players.add(new Player(StyleParameter.colorP3, "S36", 3));

        }

        public final List<Square> squares = new ArrayList<>();
        public final List<Player> players = new ArrayList<>();

        public class Player {

            public Player(Color color, String startSquare, int number) {

                playerNumber = number;
                homeSquares = new ArrayList<>();
                finishSquares = new ArrayList<>();
                occupiedSquares = new ArrayList<>();

                this.startSquare = startSquare;
                playerColor = color;

                for (int i = 0; i < 4; i++) {
                    homeSquares.add(new Square("H" + i + "P" + playerNumber));
                    finishSquares.add(new Square("F" + i + "P" + playerNumber));
                }

            }

            public List<String> getOccupiedSquares() {
                return occupiedSquares;
            }

            public void setOccupiedSquares(List<String> occupiedSquares) {
                this.occupiedSquares = occupiedSquares;
            }

            public int playerNumber;
            public Color playerColor;
            public final List<Square> homeSquares;
            public final List<Square> finishSquares;
            public List<String> occupiedSquares;
            public final String startSquare;
        }
    }

}
