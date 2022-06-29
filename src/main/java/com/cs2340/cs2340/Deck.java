package com.cs2340.cs2340;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private ArrayList<Card> cards;
    public Deck() {
        setCards(new ArrayList<>(52));
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j < 14; j++) {
                getCards().add(new Card(j, i));
            }
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(this.getCards());
    }

    /**
     * Internal constructor for testing the Blackjack game class.
     * @param cards the card list to set the deck to.
     */
    protected Deck(List<Card> cards) {
        this.setCards(new ArrayList<>(cards.size()));
        for(int i = 0; i < cards.size(); i++) {
            this.getCards().add(cards.remove(0));
        }
    }
    public Card draw() {
        // remove and return first card in card list
        // must draw from the beginning of the deck (cardList[0]) in order to work with tests
        return this.getCards().remove(1);
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
