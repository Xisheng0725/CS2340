package com.cs2340.cs2340;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test cases for the Blackjack game.
 */
public class BlackjackTest {
    private static final int TIMEOUT = 300;

    private BlackjackGame game;

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        // ensures that the game is constructed without error
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerNatural() {
        List<Card> deck = new ArrayList<>();
        // player will have a natural blackjack
        deck.add(new Card(13, 1));
        deck.add(new Card(1, 2));
        // dealer does not
        deck.add(new Card(5, 3));
        deck.add(new Card(2, 1));

        game = new BlackjackGame(deck);
        assertTrue(game.won());
    }

    @Test(timeout = TIMEOUT)
    public void testDealerNatural_PlayerLoss() {
        List<Card> deck = new ArrayList<>();
        // player will not have a natural blackjack
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2));
        // dealer will
        deck.add(new Card(10, 3));
        deck.add(new Card(1, 1));
        // player therefore must hit until tie or loss
        deck.add(new Card(7, 3));
        deck.add(new Card(8, 0)); // 22 -> loss
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        assertTrue(game.lost());
    }

    @Test(timeout = TIMEOUT)
    public void testDealerNatural_PlayerTie() {
        List<Card> deck = new ArrayList<>();
        // player will not have a natural blackjack
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2));
        // dealer will
        deck.add(new Card(10, 3));
        deck.add(new Card(1, 1));
        // player therefore must hit until tie or loss
        deck.add(new Card(7, 3));
        deck.add(new Card(7, 0)); // 21 -> tie
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        assertTrue(game.tie());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerWin_1() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 2 hits
        deck.add(new Card(11, 3)); // 17
        deck.add(new Card(3, 0)); // player: 20
        // dealer 1 hit
        deck.add(new Card(5, 3)); // dealer: 17
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        game.stand();
        assertTrue(game.won());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerWin_2() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 2 hits
        deck.add(new Card(11, 3)); // 17
        deck.add(new Card(3, 0)); // player: 20
        // dealer 2 hits
        deck.add(new Card(4, 3)); // dealer: 16
        deck.add(new Card(7, 3)); // dealer: 23
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        game.stand();
        assertTrue(game.won());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerWin_3() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 2 hits
        deck.add(new Card(11, 3)); // 17
        deck.add(new Card(3, 0)); // player: 20
        // dealer 2 hits
        deck.add(new Card(4, 3)); // dealer: 16
        deck.add(new Card(1, 3)); // dealer: 17 (ace counts as 1)
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        game.stand();
        assertTrue(game.won());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerLoss_1() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 1 hit
        deck.add(new Card(11, 3)); // player: 17
        // dealer 2 hits
        deck.add(new Card(4, 3));
        deck.add(new Card(5, 3)); // dealer: 21
        game = new BlackjackGame(deck);
        game.hit();
        game.stand();
        assertTrue(game.lost());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerLoss_2() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 2 hits
        deck.add(new Card(11, 3)); // 17
        deck.add(new Card(7, 0)); // player: 24
        // dealer 1 hits
        deck.add(new Card(5, 3)); // dealer: 17
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        assertTrue(game.lost());
    }

    @Test(timeout = TIMEOUT)
    public void testPlayerLoss_3() {
        List<Card> deck = new ArrayList<>();
        // player initial draw
        deck.add(new Card(5, 1));
        deck.add(new Card(2, 2)); // player: 7
        // dealer initial draw
        deck.add(new Card(8, 3));
        deck.add(new Card(4, 1)); // dealer: 12
        // player 2 hits
        deck.add(new Card(11, 3)); // 17
        deck.add(new Card(1, 0)); // player: 18 (ace counts as 1)
        // dealer 2 hits
        deck.add(new Card(4, 3)); // 16
        deck.add(new Card(3, 3)); // dealer: 19
        game = new BlackjackGame(deck);
        game.hit();
        game.hit();
        game.stand();
        assertTrue(game.lost());
    }
}
