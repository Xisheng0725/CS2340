package com.cs2340.cs2340;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the Card class.
 */
public class CardTest {
    private static final int TIMEOUT = 300;

    @Test(timeout = TIMEOUT)
    public void testBadInitialization() {
        // assert that Card throws when given invalid face
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(0, 1);
        });
        // assert that Card throws when given too big of a face
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(14, 1);
        });
        // assert that Card throws when given negative suit
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(5, -1);
        });
        // assert that Card throws when given too big of a suit
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(5, 4);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testAce() {
        Card ace = new Card(1, 1);
        assertEquals(11, ace.getBlackjackValue());
    }

    @Test(timeout = TIMEOUT)
    public void testNumberCards() {
        Card two = new Card(2, 3);
        assertEquals(2, two.getBlackjackValue());
        Card five = new Card(5, 0);
        assertEquals(5, five.getBlackjackValue());
        Card eight = new Card(8, 1);
        assertEquals(8, eight.getBlackjackValue());
        Card ten = new Card(10, 2);
        assertEquals(10, ten.getBlackjackValue());
    }

    @Test(timeout = TIMEOUT)
    public void testFaceCards() {
        Card jack = new Card(11, 1);
        assertEquals(10, jack.getBlackjackValue());
        Card queen = new Card(12, 3);
        assertEquals(10, queen.getBlackjackValue());
        Card king = new Card(13, 0);
        assertEquals(10, king.getBlackjackValue());
    }
}
