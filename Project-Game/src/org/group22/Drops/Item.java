package org.group22.Drops;

import org.group22.app.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Item class
 * Manage item image and item movement
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public abstract class Item {
    GamePanel gp;
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
     * @param gp  the game's game panel
     */
    public void draw(Graphics2D g2d, GamePanel gp) {
        // Calculate x and y position of item on screen
        int playerScreenX = gp.player.getScreenX();
        int playerScreenY = gp.player.getScreenY();

        int screenX = worldX - gp.player.getWorldX() + playerScreenX;
        int screenY = worldY - gp.player.getWorldY() + playerScreenY;

        // Draw item if it is on screen to save resources
        if (worldX + gp.tileSize > gp.player.getWorldX() - playerScreenX
                && worldX - gp.tileSize < gp.player.getWorldX() + playerScreenX
                && worldY + gp.tileSize > gp.player.getWorldY() - playerScreenY
                && worldY - gp.tileSize < gp.player.getWorldY() + playerScreenY) {
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage scaleImg(BufferedImage original, int width, int height) {
        BufferedImage newImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return newImg;
    }

    public static BufferedImage setupSprite(String imgPath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(Item.class.getResourceAsStream(imgPath + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
