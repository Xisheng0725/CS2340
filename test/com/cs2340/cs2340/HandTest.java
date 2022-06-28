package com.cs2340.cs2340;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the Hand class.
 */
public class HandTest {
    private static final int TIMEOUT = 300;

    private Hand hand;

    @Before
    public void initialize() {
        hand = new Hand();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        // ensures that the hand is constructed without error
    }

    @Test(timeout = TIMEOUT)
    public void test1Card_1() {
        hand.addCard(new Card(5, 0));
        assertEquals(5, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test1Card_2() {
        hand.addCard(new Card(10, 0));
        assertEquals(10, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test1Card_3() {
        hand.addCard(new Card(12, 0));
        assertEquals(10, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test1Card_4() {
        hand.addCard(new Card(1, 0));
        assertEquals(11, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_1() {
        hand.addCard(new Card(5, 0));
        hand.addCard(new Card(7, 0));
        assertEquals(12, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_2() {
        hand.addCard(new Card(3, 0));
        hand.addCard(new Card(13, 0));
        assertEquals(13, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_3() {
        hand.addCard(new Card(7, 0));
        hand.addCard(new Card(11, 0));
        assertEquals(17, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_4() {
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(7, 0));
        assertEquals(18, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_5() {
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(11, 0));
        assertEquals(21, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test2Cards_6() {
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(1, 0));
        assertEquals(12, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_1() {
        hand.addCard(new Card(5, 0));
        hand.addCard(new Card(7, 0));
        hand.addCard(new Card(5, 0));
        assertEquals(17, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_2() {
        hand.addCard(new Card(2, 0));
        hand.addCard(new Card(11, 0));
        hand.addCard(new Card(5, 0));
        assertEquals(17, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_3() {
        hand.addCard(new Card(13, 0));
        hand.addCard(new Card(11, 0));
        hand.addCard(new Card(1, 0));
        assertEquals(21, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_4() {
        hand.addCard(new Card(12, 0));
        hand.addCard(new Card(10, 0));
        hand.addCard(new Card(5, 0));
        assertEquals(25, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_5() {
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(5, 0));
        assertEquals(17, hand.getValue());
    }

    @Test(timeout = TIMEOUT)
    public void test3Cards_6() {
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(1, 0));
        hand.addCard(new Card(1, 0));
        assertEquals(13, hand.getValue());
    }
}
