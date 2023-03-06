package org.group22.GameMap;

public class Barrier extends MapComponent{

    public Barrier(Location loc) {
        setLoc(loc);
        setValidLocation(false);
        setCharHere(null);
    }

    public String toString() {
        return "Barrier at " + loc;
    }
}
