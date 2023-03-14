package org.group22.People;

import org.group22.GameMap.Location;
import org.group22.app.GamePanel;

public abstract class Enemy extends Entity {

    /**
     * Constructs new Enemy
     */
    public Enemy(GamePanel gp){
        super(gp);
    }

    /**
     * Constructs new Enemy at Location loc
     *
     * @param loc location of enemy
     */
    // public Enemy(Location loc){
    //     setLoc(loc);
    // }

    /**
     * Move this character
     */
    abstract void move();


}
