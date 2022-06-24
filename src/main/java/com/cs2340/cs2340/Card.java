package com.cs2340.cs2340;

public class Card {
    /**
     *
     * @param face
     * @param suit
     * @throws IllegalArgumentException if face or suit outside of correct range
     */
    public Card(int face, int suit) {
        // 1 -> ace
        // 2-10 -> appropriate card
        // 11 -> jack
        // 12 -> queen
        // 13 -> king
        // suit = range of 0-3
    }

    public int getBlackjackValue() {
        // faces return 10
        // aces should return 11. their dynamic behavior will have to be handled in the blackjack logic
        throw new UnsupportedOperationException("not implemented yet.");
    }
}
