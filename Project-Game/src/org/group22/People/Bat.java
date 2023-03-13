package org.group22.People;

import org.group22.GameMap.Location;

public class Bat extends Enemy {

    public Bat() {
        super();
        System.out.println("Creating " + this);
    }

    /**
     * Creates a Bat at Location loc
     *
     * @param loc location of this bat
     */
    public Bat(Location loc) {
        super(loc);
        System.out.println("Creating " + this);
    }

    /**
     * Moves bat
     * Bats follow a patrolling movement pattern
     *
     */
    @Override
    public void move(){

    }

    public String toString() {
        return "bat at " + getLoc();
    }
}
