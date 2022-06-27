package com.cs2340.cs2340;

import java.util.List;

public class Deck {

    public Deck() {

    }

    /**
     * Internal constructor for testing the Blackjack game class.
     * @param cards the card list to set the deck to.
     */
    protected Deck(List<Card> cards) {

    }

    public Card draw() {
        // remove and return first card in card list
        // must draw from the beginning of the deck (cardList[0]) in order to work with tests
        throw new UnsupportedOperationException("not implemented yet.");
    }
}
