package com.cs2340.cs2340;

/**
 * Represents a single color in the GTC game.
 */
public class GTCColor {
    public static final GTCColor Red = new GTCColor('R');
    public static final GTCColor Orange = new GTCColor('O');
    public static final GTCColor Yellow = new GTCColor('Y');
    public static final GTCColor Green = new GTCColor('G');
    public static final GTCColor Blue = new GTCColor('B');
    public static final GTCColor Purple = new GTCColor('P');
    public static final GTCColor Empty = new GTCColor('G');

    /**
     * A color in the guess was not anywhere in the blind box.
     */
    public static final GTCColor Hint_Wrong = new GTCColor('N');
    /**
     * A color in the guess was in the blind box but in the wrong location.
     */
    public static final GTCColor Hint_WrongSpot = new GTCColor('W');
    /**
     * A color in the guess was in the blind box and in the correct location.
     */
    public static final GTCColor Hint_Correct = new GTCColor('K');

    private char color;

    GTCColor(char color) {
        this.color = color;
    }

    public String toString() {
        return String.valueOf(color);
    }
}
