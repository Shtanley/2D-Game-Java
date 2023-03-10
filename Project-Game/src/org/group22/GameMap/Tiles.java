package org.group22.GameMap;
import org.group22.People.Player;

public class Tiles extends MapComponent{
    private Character charHere;
    public Tiles(Location loc) {
        setLoc(loc);
    }

//    public boolean checkItemOccupancy(){
//        return this.getItem() != null;
//    }

    public boolean checkCharOccupancy(){
        return getCharHere() != null;
    }

    public Character getCharHere() {
        return charHere;
    }

    public void setCharHere(Character charHere) {
        this.charHere = charHere;
    }
}
