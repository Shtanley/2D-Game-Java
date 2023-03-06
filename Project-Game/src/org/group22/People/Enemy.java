package org.group22.People;

import org.group22.GameMap.Location;

public abstract class Enemy extends Character {

    /**
     * Constructs new Enemy
     */
    public Enemy(){
        setLoc(null);
    }

    /**
     * Constructs new Enemy at Location loc
     *
     * @param loc location of enemy
     */
    public Enemy(Location loc){
        setLoc(loc);
    }

}
