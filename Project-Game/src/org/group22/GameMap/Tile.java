package org.group22.GameMap;

public class Tile extends MapComponent{
    public Tile(Location loc) {
        setLoc(loc);
        setCharHere(null);
        setItem(null);
        setValidLocation(true);
        System.out.println("Created " + this);
    }

    public String toString() {
        return "Tile at " + loc + " containing item: \"" + item + "\" and character: \"" + charHere + "\"";
    }
}
