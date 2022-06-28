package com.cs2340.cs2340;

public class Card {
    private int value;
    private int suit;
    /**
     *
     * @param face
     * @param suit
     * @throws IllegalArgumentException if face or suit outside of correct range
     */
    public Card(int face, int suit) {
        // suit: 0->Hearts, 1->Diamonds, 2->Clubs, 3->Spades
        this.setSuit(suit);
        // 1 -> ace
        // 2-10 -> appropriate card
        // 11 -> jack
        // 12 -> queen
        // 13 -> king
        this.setValue(getValue());
    }

    public int getBlackjackValue() {
        //Faces return 10 except ace
        if(this.getValue() == 1) {
            return 11;
        }else if(this.getValue() >= 10) {
            return 10;
        } else {
            return this.getValue();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }
}
