package Drops;

import app.GamePanel;
import app.GameSettings;

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
    private int worldX, worldY;
    private final Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    private int hitBoxDefaultX;
    private int hitBoxDefaultY;

    private final int tileSize = GameSettings.getTileSize();

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
        if (worldX + tileSize > gp.player.getWorldX() - playerScreenX
                && worldX - tileSize < gp.player.getWorldX() + playerScreenX
                && worldY + tileSize > gp.player.getWorldY() - playerScreenY
                && worldY - tileSize < gp.player.getWorldY() + playerScreenY) {
            g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
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


    public int getHitBoxDefaultX() {
        return hitBoxDefaultX;
    }

    public void setHitBoxDefaultX(int hitBoxDefaultX) {
        this.hitBoxDefaultX = hitBoxDefaultX;
    }

    public int getHitBoxDefaultY() {
        return hitBoxDefaultY;
    }

    public void setHitBoxDefaultY(int hitBoxDefaultY) {
        this.hitBoxDefaultY = hitBoxDefaultY;
    }
}
