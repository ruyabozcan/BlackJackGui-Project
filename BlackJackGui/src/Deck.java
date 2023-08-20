package com.company;

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU

import java.util.*;
import javax.swing.*;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        int index = 0;
        for (Card.FaceValue faceValue : Card.FaceValue.values())
            for (Card.Suit suit : Card.Suit.values()) {
                index++;
                deck.add(new Card(suit, faceValue, new ImageIcon("C:\\Users\\PCIdeaProjects\\BlackJackGui\\pictures" + index + ".png").getImage()));
            }
    }

    public Card dealCard() {
        int index = (int) (Math.random() * deck.size());
        return deck.remove(index);
    }

}

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU