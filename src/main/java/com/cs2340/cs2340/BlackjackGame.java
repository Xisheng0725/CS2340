package com.cs2340.cs2340;

import java.util.List;

/**
 * Contains the logic for controlling Blackjack.
 * https://bicyclecards.com/how-to-play/blackjack/
 */
public class BlackjackGame {

    public BlackjackGame() {
        // should deal player and dealer 2 cards each
    }

    /**
     * Internal constructor for testing this class.
     * @param cards the card list to set the deck to.
     */
    protected BlackjackGame(List<Card> cards) {
        // set deck = cards and then deal player and dealer 2 cards each (player first)
    }

    public void hit() {
        // deal player another card
    }

    public void stand() {
        // standing causes the dealer to deal to themselves until they stand
    }

    public boolean won() {
        throw new UnsupportedOperationException("not implemented yet.");
    }

    public boolean lost() {
        throw new UnsupportedOperationException("not implemented yet.");
    }

    public boolean tie() {
        throw new UnsupportedOperationException("not implemented yet.");
    }

    public Hand getPlayerHand() {
        throw new UnsupportedOperationException("not implemented yet.");
    }

    public Hand getDealerHand() {
        throw new UnsupportedOperationException("not implemented yet.");
    }
}