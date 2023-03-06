package org.group22.GameMap;

import org.group22.Drops.Item;
import org.group22.People.Enemy;
import org.group22.People.Hero;

import java.util.ArrayList;

public class Board {
    private MapComponent[][] map;
    private int rows;
    private int columns;
    private Location spawnPoint;
    private Location exitPoint;
    private ArrayList<Enemy> enemies;
    private ArrayList<Item> items;

    public Board(){
        this(0);
    }

    public Board(int mapNum){
        System.out.println("Creating Board");
        // Predefined maps
        if(mapNum == 0) {
            this.rows = 15;
            this.columns = 15;
            this.spawnPoint = new Location(0, 7);
            this.exitPoint = new Location(15, 7);
        }

        // Fill map
        map = new MapComponent[rows][columns];
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
