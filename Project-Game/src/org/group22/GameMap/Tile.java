package org.group22.GameMap;
import org.group22.People.Character;

public class Tile extends MapComponent{
    public Tile(Location loc) {
        setLoc(loc);
    }


    public String toString() {
        return "Tile at " + loc + " containing item: " + item + " and character: " + charHere;
    }
}
