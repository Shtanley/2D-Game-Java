package org.group22.GameMap;

import org.group22.Drops.Item;
import org.group22.People.Entity;

import java.awt.image.BufferedImage;

/**
 * MapComponent class
 * Manage component image and component movement
 *
 * abstract class for different types of map compoenents
 * @author Sameer
 * @author Michael
 * @author Dina
 */
public class MapComponent {
    protected boolean validLocation;
    protected Entity charHere;
//    protected Location loc;
    protected Item item;
    public BufferedImage image;
    public boolean collision = false;

    // Checks
    public boolean isValidLocation() {
        return validLocation;
    }

    public boolean checkItemOccupancy(){
        return this.getItem() != null;
    }

    public boolean checkCharOccupancy(){
        return getCharHere() != null;
    }

    // Getters
    public Entity getCharHere() {
        return charHere;
    }
    public Item getItem() {
        return item;
    }

//    public Location getLoc() {
//        return loc;
//    }

    // Setters
    public void setValidLocation(boolean validLocation) {
        this.validLocation = validLocation;
    }

//    public void setLoc(Location loc) {
//        this.loc = loc;
//    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setCharHere(Entity charHere) {
        if(validLocation || charHere == null) {
            this.charHere = charHere;
        } else {
            // Throw an exception?
            System.out.println("You cannot put a character at " + this);
        }
    }

//    public String toString() {
//        return "Map component at " + loc + " containing item: " + item + " and character: " + charHere;
//    }
}
