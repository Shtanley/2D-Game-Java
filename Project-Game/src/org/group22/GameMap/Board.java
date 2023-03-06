package org.group22.GameMap;

import org.group22.Drops.Item;
import org.group22.People.Enemy;
import org.group22.People.Hero;

import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * Board class
 * Creates board with barriers, enemies, and items
 *
 * @author Michael
 */
public class Board {
    private MapComponent[][] map;
    private int rows;
    private int columns;
    private Location[] barrierLocs;
    private Location[] initEnemyLocs;
    private Location spawnPoint;
    private Location exitPoint;

    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;

    public Board(){
        this(0);
    }

    public Board(int mapNum){
        System.out.println("Creating Board");
        // Hardcoded, predefined maps
        if(mapNum == 0) {
            this.rows = 15;
            this.columns = 15;
            this.spawnPoint = new Location(0, 7);
            this.exitPoint = new Location(14, 7);
            this.barrierLocs = new Location[]{
                    new Location(7, 3),
                    new Location(8, 3),
                    new Location(9, 3),
                    new Location(10, 3),
                    new Location(11, 3),
                    new Location(12, 3),
                    new Location(13, 3),
                    new Location(7, 11),
                    new Location(8, 11),
                    new Location(9, 11),
                    new Location(10, 11),
                    new Location(11, 11),
                    new Location(12, 11),
                    new Location(13, 11),
            };
        }

        // Fill map
        map = new MapComponent[rows][columns];
        // Set boundary walls
        for(int i =0 ; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(i == 0 || i == rows-1) {
                    if (j == 0 || j == columns - 1) {
                        map[i][j] = ComponentFactory.makeBarrier(i, j);
                    }
                }
            }
        }
        // Barriers
        for(int i = 0; i < barrierLocs.length; i++) {
            map[barrierLocs[i].getX()][barrierLocs[i].getY()] = ComponentFactory.makeBarrier(barrierLocs[i]);
        }
        // Set spawnPoint and exitPoint
        map[spawnPoint.getX()][spawnPoint.getY()] = ComponentFactory.makeDoor(spawnPoint.getX(), spawnPoint.getY());
        map[exitPoint.getX()][exitPoint.getY()] = ComponentFactory.makeDoor(exitPoint.getX(), exitPoint.getY());
    }

    public Location getSpawnPoint(){
        return spawnPoint;
    }

    public Location getExitPoint(){
        return exitPoint;
    }

    public void updateBoard(){

    }

}
