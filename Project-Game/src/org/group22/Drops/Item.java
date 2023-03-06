package org.group22.Drops;

import org.group22.GameMap.Location;

public abstract class Item {
    private Location loc;
    private int healthAdjustment;
    private int pointAdjustment;

    public Item() {
        System.out.println("Creating Item");
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getHealthAdjustment() {
        return healthAdjustment;
    }

    public void setHealthAdjustment(int healthAdjustment) {
        this.healthAdjustment = healthAdjustment;
    }

    public int getPointAdjustment() {
        return pointAdjustment;
    }

    public void setPointAdjustment(int pointAdjustment) {
        this.pointAdjustment = pointAdjustment;
    }
}
