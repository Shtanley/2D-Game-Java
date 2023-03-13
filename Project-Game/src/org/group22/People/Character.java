package org.group22.People;

import org.group22.GameMap.*;

public abstract class Character {
    private Location loc;

    /**
     * Get the location of this character
     *
     * @return the location of this character
     */
    public Location getLoc(){
        return loc;
    }

    /**
     * Set the location of this character
     *
     * @param newLoc new location of character
     */
    public void setLoc(Location newLoc) {
        loc = newLoc;
    }

    /**
     * Move this character
     */
    abstract void move();

    @java.lang.Override
    public java.lang.String toString() {
        return "Character{" +
                "loc=" + loc +
                '}';
    }
}
