package com.company;

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BlackJackGui {

    static final int DEALER_LIMIT = 17;
    private JFrame frame;
    private Deck deck;
    private DrawFrame drawPanel;
    private Player player;
    private Player dealer;
    private String message = "";
    private Utility help;
    private boolean gameOn;

    public static void main(String[] args) {

        BlackJackGui gui = new BlackJackGui();
        gui.init();

    }

    public void init() {
        frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new DrawFrame();
        drawPanel.setBounds(0, 0, 600, 500);
        drawPanel.setLayout(null);

        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(drawPanel);
        frame.setSize(600, 500);
        frame.setVisible(true);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(145, 415, 100, 35);
        drawPanel.add(newGameButton);

        JButton hitButton = new JButton("HIT");
        hitButton.setBounds(270, 415, 60, 35);
        drawPanel.add(hitButton);

        JButton standButton = new JButton("STAND");
        standButton.setBounds(355, 415, 75, 35);
        drawPanel.add(standButton);

        hitButton.addActionListener(new HitListener());

        newGameButton.addActionListener(new NewGameListener());

        standButton.addActionListener(new standListener());

    }


    private void setupNewGame() {

        deck = new Deck();
        player = new Player();
        dealer = new Player();
        help = new Utility();
        message = "";
        gameOn = true;

    }

    //New Game
    class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (!gameOn) {
                setupNewGame();

                for (int i = 0; i < 2; i++) {
                    dealer.addCard(deck.dealCard());
                    player.addCard(deck.dealCard());
                }

                if (help.checkBlackJack(player)) {
                    if (help.determineWinner(player, dealer) == Utility.Winner.TIE) {
                        message = "Blackjack ! Tie !";
                        gameOn = false;
                    } else {
                        message = "Blackjack ! You win !";
                        gameOn = false;
                    }
                }

                drawPanel.setDealerHand(dealer.getHand());
                drawPanel.setPlayerHand(player.getHand());
                drawPanel.setMessage(message);
                drawPanel.setGameOn(gameOn);
                frame.repaint();
            }
        }
    }

    //Hit
    class HitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (gameOn) {
                player.addCard(deck.dealCard());

                if (help.checkBust(player)) {
                    message = "Busted ! You lose !";
                    gameOn = false;
                }

                drawPanel.setPlayerHand(player.getHand());
                drawPanel.setMessage(message);
                drawPanel.setGameOn(gameOn);
                frame.repaint();
            }
        }
    }

    //Stand
    class standListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Utility.Winner winner;
            if (gameOn) {
                gameOn = false;

                while ((dealer.getValueOfHand()[0] < DEALER_LIMIT) && (dealer.getValueOfHand()[1] < DEALER_LIMIT)) {
                    dealer.addCard(deck.dealCard());
                }

                if (help.checkBust(dealer)) {
                    message = "You win !";
                    drawPanel.setMessage(message);
                    drawPanel.setGameOn(gameOn);
                    frame.repaint();
                } else {

                    winner = help.determineWinner(player, dealer);
                    switch (winner) {
                        case PLAYER:
                            message = "You win !";
                            break;
                        case DEALER:
                            message = "You lose !";
                            break;
                        case TIE:
                            message = "Tie !";
                            break;
                        default:
                            break;
                    }
                    drawPanel.setMessage(message);
                    drawPanel.setGameOn(gameOn);
                    frame.repaint();
                }
            }
        }
    }

}

class DrawFrame extends JPanel {

    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;
    String message = "";
    boolean gameOn;


    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public void paintComponent(Graphics g) {

        g.setColor(new Color(0.0f, 0.5f, 0.0f));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(new Color(1.0f, 0.0f, 0.0f));
        g.drawString(message, 240, 225);

        if (playerHand != null) {
            for (int i = 0; i < playerHand.size(); i++) {
                Image image = playerHand.get(i).getImage();
                g.drawImage(image, (240 + i * 20), (285), this);
            }
        }

        if (dealerHand != null) {
            for (int i = 0; i < dealerHand.size(); i++) {
                Image image;
                if (gameOn) {

                    if (i == 0) {
                        image = new ImageIcon("C:\\Users\\PCIdeaProjects\\BlackJackGui\\pictures\\b1fv.png").getImage();
                    } else {
                        image = dealerHand.get(i).getImage();
                    }

                } else {
                    image = dealerHand.get(i).getImage();
                }
                g.drawImage(image, (240 + i * 20), (50), this);
            }
        }
    }

}

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU