package org.group22.GameMap;

import org.group22.Drops.Item;
import org.group22.Drops.Key;
import org.group22.People.*;

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
    private int width;
    private int height;
    private Location[] barrierLocs;
    private Location[] initBatLocs;
    private Location[] initSkeletonLocs;
    private Location[] keyLocs;
    private Location spawnPoint;
    private Location exitPoint;

    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;

    /**
     * Default constructor
     * Calls constructor with mapNum = 0
     */
    public Board(){
        this(0);
    }

    /**
     * Constructs a Board using map number mapNum.
     * Generates map and instantiates enemies and items accordingly.
     *
     * @param mapNum
     */
    public Board(int mapNum){
        System.out.println("Creating Board");
        chooseMap(mapNum);
        createMap();
        createEnemies();
        createItems();
    }

    /**
     * Sets all member variables according to map number
     *
     * @param mapNum the map number
     */
    private void chooseMap(int mapNum){
        // Hardcoded, predefined maps
        if(mapNum == 0) {
            this.width = 20;
            this.height = 15;
            this.spawnPoint = new Location(0, 7);
            this.exitPoint = new Location(19, 7);
            this.barrierLocs = new Location[]{
                    new Location(5, 4),
                    new Location(6, 4),
                    new Location(7, 4),
                    new Location(8, 4),
                    new Location(9, 4),
                    new Location(10, 4),
                    new Location(11, 4),
                    new Location(12, 4),
                    new Location(13, 4),
                    new Location(14, 4),
                    new Location(5, 10),
                    new Location(6, 10),
                    new Location(7, 10),
                    new Location(8, 10),
                    new Location(9, 10),
                    new Location(10, 10),
                    new Location(11, 10),
                    new Location(12, 10),
                    new Location(13, 10),
                    new Location(14, 10),
            };
            this.initBatLocs = new Location[]{
                    new Location(10, 2),
                    new Location(10, 12),
            };
            this.initSkeletonLocs = new Location[]{
                    new Location(15, 2),
                    new Location(15, 12),
            };
            this.keyLocs = new Location[]{
                    new Location(4, 7),
                    new Location(8, 7),
                    new Location(12, 7),
                    new Location(16, 7)
            };
        }
    }

    /**
     * Generates map based on member variables
     */
    private void createMap(){
        // Fill map
        map = new MapComponent[width][height];
        // Set boundary walls
        for(int i =0 ; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(i == 0 || i == width-1) {
                    if (j == 0 || j == height - 1) {
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

    /**
     * Instantiate enemies according to member variables
     */
    private void createEnemies(){
        enemies = new ArrayList<Enemy>();
        for(int i = 0; i < initBatLocs.length; i++) {
            enemies.add(CharacterFactory.makeBat(initBatLocs[i]));
        }
        for(int i = 0; i < initSkeletonLocs.length; i++) {
            enemies.add(CharacterFactory.makeSkeleton(initSkeletonLocs[i]));
        }
    }

    /**
     * Instantiate items according to member variables
     */
    private void createItems(){
        items = new ArrayList<Item>();
        for(int i = 0; i < keyLocs.length; i++) {
            items.add(new Key(keyLocs[i]));
        }
    }

    /**
     * @return the spawn location of this map
     */
    public Location getSpawnPoint(){
        return spawnPoint;
    }

    /**
     * @return the exit location of this map
     */
    public Location getExitPoint(){
        return exitPoint;
    }

    /**
     * update this board
     */
    public void updateBoard(){

    }

}
