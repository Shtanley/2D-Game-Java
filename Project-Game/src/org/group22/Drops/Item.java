package org.group22.Drops;

import org.group22.app.GamePanel;
import org.w3c.dom.css.Rect;

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
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;
    private Rectangle hitBox = new Rectangle(0, 0, 48, 48);
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

    public static BufferedImage setupSprite(String imgPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(Item.class.getResourceAsStream(imgPath + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void setImage(BufferedImage newImage){
        image = newImage;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public boolean getCollision(){
        return collision;
    }

    public void setCollision(boolean newCollision) {
        collision = newCollision;
    }

    public int getWorldX(){
        return worldX;
    }

    public void setWorldX(int newWorldX) {
        worldX = newWorldX;
    }

    public int getWorldY(){
        return worldY;
    }

    public void setWorldY(int newWorldY) {
        worldY = newWorldY;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle newHitBox) {
        hitBox = newHitBox;
    }

}
