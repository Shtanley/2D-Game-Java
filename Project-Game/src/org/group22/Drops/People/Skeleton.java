package org.group22.Drops.People;

import org.group22.GameMap.Location;

public class Skeleton extends Enemy{

    /**
     * Constructs skeleton with null location
     */
    public Skeleton() {
        super();
        System.out.println("Creating " + this);
    }

    /**
     * Constructs skeleton at location loc
     * @param loc location of skeleton
     */
    public Skeleton(Location loc) {
        super(loc);
        System.out.println("Creating " + this);
    }

    /**
     * Moves skeleton
     * Skeletons have a tracking movement pattern
     *
     */
    public void move(){

    }

    public String toString() {
        return "skeleton at " + getLoc();
    }
}
