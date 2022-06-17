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
        GTCColor[] guess = new GTCColor[] { GTCColor.Purple, GTCColor.Red, GTCColor.Blue, GTCColor.Green };
        game.guess(guess);
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_allWrong() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Purple, GTCColor.Purple, GTCColor.Yellow, GTCColor.Yellow };
        game.guess(guess);

        GTCColor[] allWrong = new GTCColor[] { GTCColor.Hint_Wrong, GTCColor.Hint_Wrong, GTCColor.Hint_Wrong, GTCColor.Hint_Wrong };
        assertArrayEquals(allWrong, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_1() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Blue, GTCColor.Red, GTCColor.Yellow, GTCColor.Yellow };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot, GTCColor.Hint_Wrong, GTCColor.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_2() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Yellow, GTCColor.Green, GTCColor.Orange, GTCColor.Purple };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot, GTCColor.Hint_Wrong, GTCColor.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_3() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Green, GTCColor.Green, GTCColor.Orange, GTCColor.Purple };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot, GTCColor.Hint_WrongSpot, GTCColor.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_4() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Orange, GTCColor.Green, GTCColor.Orange, GTCColor.Red };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot, GTCColor.Hint_WrongSpot, GTCColor.Hint_WrongSpot};
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_5() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Purple, GTCColor.Green, GTCColor.Yellow, GTCColor.Orange };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_Wrong, GTCColor.Hint_Wrong };
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_6() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Orange, GTCColor.Red };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot, GTCColor.Hint_WrongSpot};
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_partlyCorrect_8() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Green };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_WrongSpot};
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testGiveHint_allRight() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.guess(guess);

        GTCColor[] hint = new GTCColor[] { GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_Correct, GTCColor.Hint_Correct};
        assertArrayEquals(hint, game.getHint());
    }

    @Test(timeout = TIMEOUT)
    public void testLose() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] wrongGuess = new GTCColor[] { GTCColor.Blue, GTCColor.Blue, GTCColor.Blue, GTCColor.Blue };

        for (int i = 0; i < ColorGame.MAX_GUESSES; i++) {
            game.guess(wrongGuess);
        }
        assertTrue(game.hasLost());
    }

    @Test(timeout = TIMEOUT)
    public void testWin() {
        GTCColor[] blind = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.setBlind(blind);

        GTCColor[] guess = new GTCColor[] { GTCColor.Blue, GTCColor.Green, GTCColor.Red, GTCColor.Orange };
        game.guess(guess);

        assertTrue(game.hasWon());
    }
}
