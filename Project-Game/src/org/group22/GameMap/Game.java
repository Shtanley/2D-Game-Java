package org.group22.GameMap;

public class Game {
    private static final Board gameBoard = new Board();
    private double time;
    private int difficulty;
    private Location spawnPoint;
    private Location exitPoint;

    /**
     * Returns an instance of GameMap.
     * Sets up the map, enemies, hero, and runs updates the game.
     *
     * @param time
     * @param difficulty
     * @param spawnPoint
     * @param exitPoint
     */
    public Game(double time, int difficulty, Location spawnPoint, Location exitPoint) {
        this.time = time;
        this.difficulty = difficulty;
        this.spawnPoint = spawnPoint;
        this.exitPoint = exitPoint;

        setUpMap();
        setUpEnemy();
        setUpHero();
        update();
    }

    private void setUpMap() {

    }

    private void setUpEnemy() {

    }

    private void setUpHero() {

    }

    public void update() {

    }
}
