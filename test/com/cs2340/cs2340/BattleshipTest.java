package com.cs2340.cs2340;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BattleshipTest {
    private static final int TIMEOUT = 300;

    private BattleshipLogic game;

    @Before
    public void init() {
        game = new BattleshipLogic();
        game.addPattern(0);
    }

    @Test(timeout = TIMEOUT)
    public void testMissFar() {
        var result = game.isHit(7, 7);
        assertEquals(result, -1);
    }

    @Test(timeout = TIMEOUT)
    public void testMissClose() {
        var result = game.isHit(2, 5);
        assertEquals(result, -1);
    }

    @Test(timeout = TIMEOUT)
    public void testHit2() {
        var result = game.isHit(2, 2);
        assertEquals(result, 1);
    }

    @Test(timeout = TIMEOUT)
    public void testHit3() {
        var result = game.isHit(1, 7);
        assertEquals(result, 1);
    }

    @Test(timeout = TIMEOUT)
    public void testHit4() {
        var result = game.isHit(3, 0);
        assertEquals(result, 1);
    }

    @Test(timeout = TIMEOUT)
    public void testLoss() {
        while (game.getMaxGuess() > 0) {
            game.isHit(game.getMaxGuess() % 8, game.getMaxGuess() / 8);
        }
        assertEquals(game.winOrLose(), -1);
    }

    @Test(timeout = TIMEOUT)
    public void testWin() {
        game.isHit(2, 0);
        game.isHit(3, 0);
        game.isHit(4, 0);
        game.isHit(5, 0);

        game.isHit(1, 2);
        game.isHit(2, 2);

        game.isHit(3, 5);
        game.isHit(4, 5);

        game.isHit(1, 5);
        game.isHit(1, 6);
        game.isHit(1, 7);

        game.isHit(6, 2);
        game.isHit(6, 3);
        game.isHit(6, 4);

        assertEquals(game.winOrLose(), 1);
    }

    @Test(timeout = TIMEOUT)
    public void testWinWithNoGuessesLeft() {
        game.isHit(2, 0);
        game.isHit(3, 0);
        game.isHit(4, 0);
        game.isHit(5, 0);

        game.isHit(1, 2);
        game.isHit(2, 2);

        game.isHit(3, 5);
        game.isHit(4, 5);

        game.isHit(1, 5);
        game.isHit(1, 6);
        game.isHit(1, 7);

        game.isHit(6, 2);
        game.isHit(6, 3);

        Random random = new Random();
        while (game.getMaxGuess() > 1) {
            // guess random spots to whittle down remaining guesses
            // but make sure we don't guess the last ship tile
            int x, y;
            do {
                x = random.nextInt(8);
                y = random.nextInt(8);
            } while (x == 6 && y == 4);
            game.isHit(x, y);
        }

        game.isHit(6, 4);

        assertEquals(game.winOrLose(), 1);
    }
}
