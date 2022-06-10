package com.cs2340.cs2340;

/**
 * Contains the logic for controlling the GTC game.
 */
public class ColorGame {
    public static final int MAX_GUESSES = 9;

    public ColorGame() {

    }

    /**
     * Enters a guess.
     * @param colors the guess. Must be of length 4.
     */
    public void guess(Color[] colors) {

    }

    /**
     * Gets the 4 hints for the most recent guess.
     * @return the 4 hints for the most recent guess.
     */
    public Color[] getHint() {
        throw new UnsupportedOperationException("Not yet implemented"); // so that this class compiles
    }

    /**
     * Gets whether the game has been won.
     * @return whether the game has been won.
     */
    public boolean hasWon() {
        throw new UnsupportedOperationException("Not yet implemented"); // so that this class compiles
    }

    /**
     * Gets whether the game has been lost.
     * @return whether the game has been lost.
     */
    public boolean hasLost() {
        throw new UnsupportedOperationException("Not yet implemented"); // so that this class compiles
    }

    /**
     * Method for testing this class.
     * @param colors The colors to set the blind box to.
     */
    protected void setBlind(Color[] colors) {

    }
}
