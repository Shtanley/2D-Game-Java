package org.group22.People;

import org.group22.GameMap.*;

public abstract class Character {
    private Location loc;

    public Location getLoc(){
        return loc;
    }

    abstract void move();
}
