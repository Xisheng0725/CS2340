package com.cs2340.cs2340;

/**
 * Represents a single color in the GTC game.
 */
public class Color {
    public static final Color Yellow = new Color('Y');
    public static final Color Orange = new Color('O');
    public static final Color Pink = new Color('P');
    public static final Color Blue = new Color('B');
    public static final Color Red = new Color('R');
    public static final Color Green = new Color('G');

    /**
     * A color in the guess was not anywhere in the blind box.
     */
    public static final Color Hint_Wrong = new Color('N');
    /**
     * A color in the guess was in the blind box but in the wrong location.
     */
    public static final Color Hint_White = new Color('W');
    /**
     * A color in the guess was in the blind box and in the correct location.
     */
    public static final Color Hint_Black = new Color('K');

    private char color;

    private Color(char color) {
        this.color = color;
    }
}
