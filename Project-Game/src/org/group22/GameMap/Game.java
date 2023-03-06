package org.group22.GameMap;

import org.group22.People.Hero;

/**
 * Game class
 * Creates the game with
 *
 */
public class Game {
    private static Board gameBoard;
    private int mapNum;
    private static final int finalMap = 0;
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
     * Creates the board, hero, and updates the game.
     *
     * @param difficulty the difficulty of the game
     */
    public Game(int difficulty) {
        System.out.println("Creating Game");
        this.time = 0;
        this.difficulty = difficulty;
        this.mapNum = 0;
        this.hero = new Hero();
        setMap(mapNum);

        update();
    }

    /**
     * Initializes gameBoard to map number mapNum
     * Sets hero's location to the new map's spawnPoint
     *
     * @param mapNum the number of the map to change to
     */
    private void setMap(int mapNum) {
        this.mapNum = mapNum;
        this.gameBoard = new Board(mapNum);
        hero.setLoc(gameBoard.getSpawnPoint());
    }


    /**
     * Updates the state of the game
     *
     */
    public void update() {
        gameBoard.updateBoard();
        ticks += 1;
        // Check hero's health
        if(hero.dead()){
            //game is over
        }
        if(hero.getLoc() == gameBoard.getExitPoint()) {
            if(mapNum == finalMap) {
                // Player wins
                
            } else {
                // Move to next level
                setMap(mapNum++);
            }
        }
    }
}
