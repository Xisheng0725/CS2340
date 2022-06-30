package com.cs2340.cs2340;

import java.util.List;

/**
 * Contains the logic for controlling Blackjack.
 * https://bicyclecards.com/how-to-play/blackjack/
 */
public class BlackjackGame {
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;

    public BlackjackGame() {
        playerHand = new Hand();
        dealerHand = new Hand();
        deck = new Deck();
        for (int i = 0; i < 2; i++) {
            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());
        }
    }

    /**
     * Internal constructor for testing this class.
     * @param cards the card list to set the deck to.
     */
    protected BlackjackGame(List<Card> cards) {
        deck = new Deck(cards);

    }

    public int hit() {
        // deal player another card
        Card temp = deck.draw();
        playerHand.addCard(temp);
        return temp.getBlackjackValue();
    }

    public void stand() {
        if(deck.getCards().isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        //standing causes the dealer to deal to themselves until they stand
        while(dealerHand.getValue() < 16 && playerHand.getValue() >= dealerHand.getValue()) {
            Card temp = deck.draw();
            dealerHand.addCard(temp);
        }
        System.out.println(dealerHand.getValue());
    }


    public boolean won() {
        if(playerHand.getValue() > dealerHand.getValue() && playerHand.getValue() <= 21) {
            return true;
        } else if(playerHand.getValue() <= 21 && dealerHand.getValue() > 21) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lost() {
        if(!won()) {return true;} else {return false;}
    }

    public boolean tie() {
        if(playerHand.getValue() == dealerHand.getValue() && playerHand.getValue() <= 21) {
            return true;
        }
        return false;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }
}