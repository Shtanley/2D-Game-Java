package org.group22.GameMap;

public class Door extends MapComponent{
    public Door(Location loc) {
        setLoc(loc);
    }

    public String toString() {
        return "Door at " + loc + " containing item: " + item + " and character: " + charHere;
    }
}
