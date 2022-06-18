package com.cs2340.cs2340;

/**
 * Represents a single color in the GTC game.
 */
public class GTCColor {
    public static final GTCColor Red = new GTCColor("red");
    public static final GTCColor Orange = new GTCColor("orange");
    public static final GTCColor Yellow = new GTCColor("yellow");
    public static final GTCColor Green = new GTCColor("green");
    public static final GTCColor Blue = new GTCColor("blue");
    public static final GTCColor Purple = new GTCColor("purple");
    public static final GTCColor Empty = new GTCColor("e");

    /**
     * A color in the guess was not anywhere in the blind box.
     */
    public static final GTCColor Hint_Wrong = new GTCColor("N");
    /**
     * A color in the guess was in the blind box but in the wrong location.
     */
    public static final GTCColor Hint_WrongSpot = new GTCColor("W");
    /**
     * A color in the guess was in the blind box and in the correct location.
     */
    public static final GTCColor Hint_Correct = new GTCColor("K");

    private String color;

    GTCColor(String color) {
        this.color = color;
    }

    public String toString() {
        return String.valueOf(color);
    }

    public String getColor() {return this.color;}
}
