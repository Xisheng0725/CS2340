package com.cs2340.cs2340;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for the Guess the Color game.
 */
public class GuessTheColorGameTest {
    private static final int TIMEOUT = 300;

    private ColorGame game;

    @Before
    public void initialize() {
        game = new ColorGame();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        // ensures that the game is constructed without error
    }

    @Test(timeout = TIMEOUT)
    public void testGuess() {
        // ensures that the game can guess without error
        Color[] guess = new Color[] { Color.Pink, Color.Red, Color.Blue, Color.Green };
        game.guess(guess);
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_allWrong() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Pink, Color.Pink, Color.Yellow, Color.Yellow };
        game.guess(guess);

        Color[] allWrong = new Color[] { Color.Hint_Wrong, Color.Hint_Wrong, Color.Hint_Wrong, Color.Hint_Wrong };
        assertArrayEquals(allWrong, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_1() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Blue, Color.Red, Color.Yellow, Color.Yellow };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_White, Color.Hint_Wrong, Color.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_2() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Yellow, Color.Green, Color.Orange, Color.Pink };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_White, Color.Hint_Wrong, Color.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_3() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Green, Color.Green, Color.Orange, Color.Pink };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_White, Color.Hint_White, Color.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_4() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Orange, Color.Green, Color.Orange, Color.Red };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_White, Color.Hint_White, Color.Hint_White };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_5() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Pink, Color.Green, Color.Yellow, Color.Orange };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_White, Color.Hint_Wrong, Color.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_6() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Blue, Color.Green, Color.Orange, Color.Red };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_Black, Color.Hint_White, Color.Hint_White };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_8() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Green };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_Black, Color.Hint_Black, Color.Hint_White };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_allRight() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.guess(guess);

        Color[] hint = new Color[] { Color.Hint_Black, Color.Hint_Black, Color.Hint_Black, Color.Hint_Black };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testLose() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] wrongGuess = new Color[] { Color.Blue, Color.Blue, Color.Blue, Color.Blue };

        for (int i = 0; i < ColorGame.MAX_GUESSES; i++) {
            game.guess(wrongGuess);
        }
        assertTrue(game.hasLost());
    }

    @Test(timeout = TIMEOUT)
    public void testWin() {
        Color[] blind = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.setBlind(blind);

        Color[] guess = new Color[] { Color.Blue, Color.Green, Color.Red, Color.Orange };
        game.guess(guess);

        assertTrue(game.hasWon());
    }
}
