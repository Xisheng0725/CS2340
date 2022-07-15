package com.cs2340.cs2340;

import javafx.scene.control.Alert;

import java.util.Random;

public class BattleshipLogic {

    private char[][] pattern;


    private int maxGuess;

    private BattleshipPattern BSP;

    private int numRemain;

    private Random rand;

    // get pattern and initialize game when call this constructor
    public BattleshipLogic() {
        this.BSP = new BattleshipPattern();
        this.rand = new Random();
        this.maxGuess = 30;
        this.numRemain = 14;
    }

    public void addPattern() {
        pattern = BSP.getPattern(rand.nextInt(6));
    }

    public void addPattern(int index) {
        pattern = BSP.getPattern(index);
    }

    /* to check whether hit the ship or not, should call this after each hit
     *
     */
    public int isHit(int x, int y) {
        if (pattern[x][y] == '.') {
            maxGuess--;
            pattern[x][y] = 'X';
            return -1;
        } else if (pattern[x][y] == '0' || pattern[x][y] == '1'
                   || pattern[x][y] == '2' || pattern[x][y] == '3'
                   || pattern[x][y] == '4') {
            maxGuess--;
            numRemain--;
            pattern[x][y] = 'X';
            return 1;
        } else {
            return 0;
        }
    }
    /* to check the game is win or lost
     * @return int -1 is lose, 0 is not finished, 1 is win
    */

    public int winOrLose() {
        if (maxGuess == 0 && numRemain != 0) {
            return -1;
        }

        if (numRemain == 0) {
            return 1;
        }
        return 0;
    }

    public char[][] getPattern() {
        return pattern;
    }

    public int getMaxGuess() {
        return maxGuess;
    }

    public int getNumRemain() {
        return numRemain;
    }
}
