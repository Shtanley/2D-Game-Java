package org.group22.GameMap;

public class Door extends MapComponent{
    public Door(Location loc) {
        setLoc(loc);
        setItem(null);
        System.out.println("Created " + this);
    }

    public String toString() {
        return "Door at " + loc + " containing item: \"" + item + "\" and character: \"" + charHere + "\"";
    }
}
