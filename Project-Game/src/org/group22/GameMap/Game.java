package org.group22.GameMap;

import org.group22.People.Hero;

public class Game {
    private static Board gameBoard;
    private int mapNum;
    private static Hero hero;
    private double time;
    private int ticks;
    private int difficulty;

    /**
     * Default constructor
     * Calls constructor with difficulty = 0
     */
    public Game() {
        this(0);
    }

    /**
     * Returns an instance of GameMap with given difficulty.
     * Sets up the map, enemies, hero, and runs updates the game.
     *
     * @param difficulty the difficulty of the game
     */
    public Game(int difficulty) {
        System.out.println("Creating Game");
        this.time = 0;
        this.difficulty = difficulty;
        this.mapNum = 0;

        this.gameBoard = new Board(0);
        this.hero = new Hero();
        update();
    }

    /**
     * Re-initializes gameBoard to new map
     *
     * @param newMapNum the number of the map to change to
     */
    private void changeMap(int newMapNum) {
        mapNum = newMapNum;
    }


    /**
     * Updates the state of the game
     */
    public void update() {
        gameBoard.updateBoard();
        ticks += 1;
        // Check hero's health
        if(hero.dead()){
            //game is over
        }
        if(hero.getLoc() == gameBoard.getExitPoint()) {
            // Move to next level
        }
    }
}
