package org.group22.GameMap;

import org.group22.Drops.*;
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
        printBoard();
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
        map = new MapComponent[width][height];
        // Set boundary barriers
        for(int i =0 ; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(i == 0 || i == width-1 || j == 0 || j == height - 1) {
                    map[i][j] = ComponentFactory.makeBarrier(i, j);
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
        for (Enemy e: enemies){
           map[e.getLoc().getX()][e.getLoc().getY()] = ComponentFactory.makeTile(e.getLoc());
           map[e.getLoc().getX()][e.getLoc().getY()].setCharHere(e);
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

        for (Item i: items){
            map[i.getLoc().getX()][i.getLoc().getY()] = ComponentFactory.makeTile(i.getLoc());
            map[i.getLoc().getX()][i.getLoc().getY()].setItem(i);
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

    public void printBoard(){
        for (int h = 0; h < height; h++){
            for (int w = 0; w < width; w++) {
                if (map[w][h] == null){
                    System.out.print("- ");
                } else if (map[w][h].getClass().equals(Door.class)) {
                    System.out.print("d ");
                } else if (map[w][h].getClass().equals(Wall.class)) {
                    System.out.print("w ");
                } else if (map[w][h].getClass().equals(Barrier.class)) {
                    System.out.print("b ");
                } else {
                    if (map[w][h].getCharHere() != null) {
                        if (map[w][h].getCharHere().getClass().equals(Hero.class)) {
                            System.out.print("H ");
                        } else if (map[w][h].getCharHere().getClass().equals(Skeleton.class)) {
                            System.out.print("s ");
                        } else if (map[w][h].getCharHere().getClass().equals(Bat.class)) {
                            System.out.print("c ");
                        }
                    } else if (map[w][h].getItem() != null) {
                        if (map[w][h].getItem().getClass().equals(Potion.class)) {
                            System.out.print("p ");
                        } else if (map[w][h].getItem().getClass().equals(Key.class)) {
                            System.out.print("k ");
                        } else if (map[w][h].getItem().getClass().equals(Punishment.class)) {
                            System.out.print("x ");
                        }
                    } else {
                        System.out.print("t ");
                    }
                }
            }
            System.out.print("\n");
        }
    }

}
