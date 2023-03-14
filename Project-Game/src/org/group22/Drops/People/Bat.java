package org.group22.Drops.People;

import org.group22.GameMap.Location;

public class Bat extends Enemy {
    private boolean horizontal;
    private boolean forwardBackward;
    public Bat() {
        super();
        System.out.println("Creating " + this);
    }

    /**
     * Creates a Bat at Location loc
     *
     * @param loc location of this bat
     */
    public Bat(Location loc, boolean horizontal) {
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
        //condition if forward is
        boolean collision = true;
        forwardBackward = collision ? false : true;

        if (horizontal){
            if (forwardBackward){
                setLoc(new Location(getLoc().getX() + 1, getLoc().getY()));
            } else {
                setLoc(new Location(getLoc().getX() - 1, getLoc().getY()));
            }
        } else {
            if (forwardBackward){
                setLoc(new Location(getLoc().getX(), getLoc().getY() + 1));
            } else {
                setLoc(new Location(getLoc().getX(), getLoc().getY() - 1));
            }
        }
    }

    public String toString() {
        return "bat at " + getLoc();
    }
}
