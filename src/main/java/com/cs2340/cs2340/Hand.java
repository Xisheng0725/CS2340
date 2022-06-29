package com.cs2340.cs2340;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    ArrayList<Card> hand;
    public Hand() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getValue() {
        int aceCount = 0;
        int total = 0;

        for(Card card : hand) {
            if(card.getBlackjackValue() == 11) {
                aceCount++;
            }
            total += card.getBlackjackValue();
        }
        while(total > 21 && aceCount != 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    public List<Card> getHand() {
        return this.hand;
    }
}
