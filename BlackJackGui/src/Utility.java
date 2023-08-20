package com.company;

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU

public class Utility {

    static final int BLACK_JACK = 21;

    public enum Winner {PLAYER, DEALER, TIE}

    public Winner determineWinner(Player player, Player dealer) {

        int biggestPlayerHand;
        int biggestDealerHand;

        if (player.getValueOfHand()[0] > BLACK_JACK) {
            biggestPlayerHand = player.getValueOfHand()[1];
        } else if (player.getValueOfHand()[1] > BLACK_JACK) {
            biggestPlayerHand = player.getValueOfHand()[0];
        } else if (player.getValueOfHand()[0] >= player.getValueOfHand()[1]) {
            biggestPlayerHand = player.getValueOfHand()[0];
        } else {
            biggestPlayerHand = player.getValueOfHand()[1];
        }

        if (dealer.getValueOfHand()[0] > BLACK_JACK) {
            biggestDealerHand = dealer.getValueOfHand()[1];
        } else if (dealer.getValueOfHand()[1] > BLACK_JACK) {
            biggestDealerHand = dealer.getValueOfHand()[0];
        } else if (dealer.getValueOfHand()[0] >= dealer.getValueOfHand()[1]) {
            biggestDealerHand = dealer.getValueOfHand()[0];
        } else {
            biggestDealerHand = dealer.getValueOfHand()[1];
        }

        if (biggestPlayerHand == biggestDealerHand) {
            return Winner.TIE;
        } else if (biggestPlayerHand > biggestDealerHand) {
            return Winner.PLAYER;
        } else {
            return Winner.DEALER;
        }

    }

    public boolean checkBlackJack(Player player) {
        if ((player.getValueOfHand()[0] == BLACK_JACK) || (player.getValueOfHand()[1] == BLACK_JACK)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkBust(Player player) {
        if ((player.getValueOfHand()[0] > BLACK_JACK) && (player.getValueOfHand()[1] > BLACK_JACK)) {
            return true;
        } else {
            return false;
        }
    }
}

//RUYA BOZCAN
//COMPUTER ENGINEERING
//ABU