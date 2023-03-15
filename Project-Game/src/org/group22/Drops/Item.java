package org.group22.Drops;

import org.group22.GameMap.Location;
import org.group22.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Item class
 * Manage item image and item movement
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public abstract class Item {
    private Location loc;
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public int hitBoxDefaultX, hitBoxDefaultY;

    public abstract int getHealthAdjustment();
    public abstract int getPointAdjustment();

    /**
     * Draw this item if it is on the screen
     *
     * @param g2d 2d graphics handler
     * @param gp the game's game panel
     */
    public void draw(Graphics2D g2d, GamePanel gp) {
        // Calculate x and y position of item on screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Draw item if it is on screen to save resources
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    @java.lang.Override
    public java.lang.String toString() {
        return name + " {" +
                "loc=" + loc +
                ", healthAdjustment=" + getHealthAdjustment() +
                ", pointAdjustment=" + getPointAdjustment() +
                '}';
    }
}
