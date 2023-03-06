package org.group22.GameMap;

import org.group22.Drops.Item;

public abstract class MapComponent {
    private boolean validLocation;
    private Location loc;
    private Item item;

    public boolean isValidLocation() {
        return validLocation;
    }

    public void setValidLocation(boolean validLocation) {
        this.validLocation = validLocation;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
