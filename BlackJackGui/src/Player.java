package com.company;

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU

import java.util.*;

public class Player {

    private ArrayList<Card> hand = new ArrayList<>();
    //valueOfHand[0] ace 11 (there is no ace) 11 1 1 1 1
    //valueOfHand[1] ace 1 (there is ace) 1 1 1
    private int[] valueOfHand;

    public Player() {
        valueOfHand = new int[2];
    }

    public void addCard(Card card) {

        boolean aceFound = false;

        if (getHand().size() > 0) {
            for (Card index : getHand()) {
                if (index.getFaceValue() == Card.FaceValue.ACE) {
                    aceFound = true;
                }
            }
        }

        getHand().add(card);

        if (card.getFaceValue() == Card.FaceValue.ACE) {
            if (aceFound) {
                valueOfHand[0] += 1;
            } else {
                valueOfHand[0] += 11;
            }
            valueOfHand[1] += 1;
        } else {
            valueOfHand[0] += card.getFaceValue().getIntValue();
            valueOfHand[1] += card.getFaceValue().getIntValue();
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int[] getValueOfHand() {
        return valueOfHand;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.hand.size(); i++) {
            sb.append(hand.get(i).getFaceValue().toString() + "of" + hand.get(i).getSuit().toString() + " ");
        }
        return sb.toString();
    }

}

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU