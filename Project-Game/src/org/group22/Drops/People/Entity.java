package org.group22.Drops.People;

import org.group22.GameMap.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private Location loc;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, right1, right2, down1, down2, left1, left2;
    public String direction;

    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;

    /**
     * Get the location of this character
     *
     * @return the location of this character
     */
    public Location getLoc(){
        return loc;
    }

    /**
     * Set the location of this character
     *
     * @param newLoc new location of character
     */
    public void setLoc(Location newLoc) {
        loc = newLoc;
    }

    @Override
    public java.lang.String toString() {
        return "Character{" +
                "loc=" + loc +
                '}';
    }
}
