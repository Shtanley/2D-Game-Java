package org.group22.Drops;

import org.group22.GameMap.Game;
import org.group22.GameMap.Location;
import org.group22.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Item class
 * Manage item image and item movement
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Item {
    private Location loc;
    private int healthAdjustment;
    private int pointAdjustment;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public int hitBoxDefaultX, hitBoxDefaultY;

    public Item() {
    }

    public Item(Location loc) {
        setLoc(loc);
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

    public void draw(Graphics2D g2d, GamePanel gp) {
        // Calculate x and y position of tile on screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Draw tile if it is on screen to save resources
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Item{" +
                "loc=" + loc +
                ", healthAdjustment=" + healthAdjustment +
                ", pointAdjustment=" + pointAdjustment +
                '}';
    }
}
