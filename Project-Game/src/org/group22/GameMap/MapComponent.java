package org.group22.GameMap;

import org.group22.Drops.Item;

import java.awt.image.BufferedImage;

/**
 * MapComponent class
 * Manage component image and component movement
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class MapComponent {
    private boolean validLocation;
    private Location loc;
    private Item item;
    public BufferedImage image;
    public boolean collision = false;

//    public boolean isValidLocation() {
//        return validLocation;
//    }
//
//    public void setValidLocation(boolean validLocation) {
//        this.validLocation = validLocation;
//    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

//    public Item getItem() {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
//    }
}
