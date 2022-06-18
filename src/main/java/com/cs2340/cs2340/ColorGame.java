package com.cs2340.cs2340;

import java.util.Random;

/**
 * Contains the logic for controlling the GTC game.
 */
public class ColorGame {
    public static final int MAX_GUESSES = 8;
    public static final int GUESS_SIZE = 4;

    private static final GTCColor[] colorPossibilities = new GTCColor[] {
            GTCColor.Red,
            GTCColor.Orange,
            GTCColor.Yellow,
            GTCColor.Green,
            GTCColor.Blue,
            GTCColor.Purple
    };

    private GTCColor[] blind;

    private int numberGuesses;
    private GTCColor[] mostRecentHint;

    public ColorGame() {
        numberGuesses = 0;
        blind = new GTCColor[GUESS_SIZE];
        Random random = new Random();
        for (int i = 0; i < GUESS_SIZE; i++) {
            blind[i] = colorPossibilities[random.nextInt(colorPossibilities.length)];
        }
    }

    /**
     * Enters a guess.
     * @param colors the guess. Must be of length GUESS_SIZE.
     */
    public void guess(GTCColor[] colors) {
        if (numberGuesses >= MAX_GUESSES) {
            throw new UnsupportedOperationException("Cannot guess more than MAX_GUESSES times.");
        }
        if (colors.length != GUESS_SIZE) {
            throw new IllegalArgumentException("Guess must be of length GUESS_SIZE.");
        }

        int countCorrect = 0;
        int countWrongSpot = 0;

        // Check the guess
        for (int i = 0; i < colors.length; i++) {
            // If the color is in the right spot, the player gets a 'correct' hint
            if (colors[i] == blind[i]) {
                countCorrect++;
            } else {
                // If it's in the wrong spot, check the rest of the blind to see if the
                // guess shows up somewhere else
                blindCheck: for (int j = 0; j < blind.length; j++) {
                    // If it does, give a 'wrong spot' hint
                    if (colors[i] == blind[j]) {
                        countWrongSpot++;
                        break blindCheck;
                    }
                }
            }
        }

        // Create the hint array from the 'correct' and 'wrong spot' hint counts
        mostRecentHint = new GTCColor[GUESS_SIZE];
        for (int i = 0; i < mostRecentHint.length; i++) {
            if (i < countCorrect) {
                mostRecentHint[i] = GTCColor.Hint_Correct;
            } else if (i < countCorrect + countWrongSpot) {
                mostRecentHint[i] = GTCColor.Hint_WrongSpot;
            } else {
                mostRecentHint[i] = GTCColor.Hint_Wrong;
            }
        }

        numberGuesses++;
    }

    /**
     * Gets the hints for the most recent guess.
     * @return the hints for the most recent guess.
     */
    public GTCColor[] getHint() {
        return mostRecentHint;
    }

    /**
     * Gets whether the game has been won.
     * @return whether the game has been won.
     */
    public boolean hasWon() {
        if(mostRecentHint == null) {
            return false;
        }
        for (int i = 0; i < GUESS_SIZE; i++) {
            if (mostRecentHint[i] != GTCColor.Hint_Correct) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets whether the game has been lost.
     * @return whether the game has been lost.
     */
    public boolean hasLost() {
        return numberGuesses == MAX_GUESSES && !hasWon();
    }

    /**
     * Gets the blind colors.
     * @return the blind colors.
     */
    public GTCColor[] getBlind() {
        return blind;
    }

    /**
     * Method for testing this class.
     * @param colors The colors to set the blind box to.
     */
    protected void setBlind(GTCColor[] colors) {
        blind = colors;
    }

    /**
     * Getter method for numGuesses
     * @return number of guesses made so far
     */
    public int getNumGuesses() {
       return this.numberGuesses;
    }

    public void reset() {
        numberGuesses = 0;
        blind = new GTCColor[GUESS_SIZE];
        Random random = new Random();
        for (int i = 0; i < GUESS_SIZE; i++) {
            blind[i] = colorPossibilities[random.nextInt(colorPossibilities.length)];
        }
    }
}
