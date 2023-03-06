package org.group22.GameMap;

public class Wall extends Barrier {

    public Wall(Location loc) {
        super(loc);
    }


    public String toString() {
        return "Wall at " + loc;
    }
}
