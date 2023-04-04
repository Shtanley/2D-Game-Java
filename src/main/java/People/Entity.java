package main.java.People;

import main.java.app.GamePanel;
import main.java.app.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public abstract class Entity {
    GamePanel gp;
    private int worldX, worldY;
    private int speed;

    private BufferedImage up1, up2, up3, up4, right1, right2, right3, right4, down1, down2, down3, down4, left1, left2, left3, left4;
    private String direction;

    private int spriteCount = 0;
    private int spriteNum = 1;
    private Rectangle hitBox;
    private int hitBoxDefaultX, hitBoxDefaultY;
    private boolean collisionOn = false;
    private int lockActionCount;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void update();

    /**
     * Sets up the sprite located at imgPath
     *
     * @param imgPath the path to the file containing the image to set up
     * @return a BufferedImage of the image
     */
    public static BufferedImage setupSprite(String imgPath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream(imgPath + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Draws this entity to the screen
     *
     * @param g2d the Graphics2D objects which handles graphics
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        // Calculate x and y position of tile on screen
        int playerScreenX = gp.player.getScreenX();
        int playerScreenY = gp.player.getScreenY();
        int playerWorldX = gp.player.getWorldX();
        int playerWorldY = gp.player.getWorldY();
        int screenX = worldX - playerWorldX + playerScreenX;
        int screenY = worldY - playerWorldY + playerScreenY;
        int tileSize = GameSettings.getTileSize();

        // Draw this entity if it is on the screen
        if (worldX + tileSize > playerWorldX - playerScreenX && worldX - tileSize < playerWorldX + playerScreenX
            && worldY + tileSize > playerWorldY - playerScreenY && worldY - tileSize < playerWorldY + playerScreenY) {
            switch (direction) {
                case "up" -> image = switch (spriteNum) {
                    case 1 -> up1;
                    case 2 -> up2;
                    case 3 -> up3;
                    default -> up4;
                };
                case "down" -> image = switch (spriteNum) {
                    case 1 -> down1;
                    case 2 -> down2;
                    case 3 -> down3;
                    default -> down4;
                };
                case "left" -> image = switch (spriteNum) {
                    case 1 -> left1;
                    case 2 -> left2;
                    case 3 -> left3;
                    default -> left4;
                };
                case "right" -> image = switch (spriteNum) {
                    case 1 -> right1;
                    case 2 -> right2;
                    case 3 -> right3;
                    default -> right4;
                };
            }
            g2d.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldX(int x){
        worldX = x;
    }

    public void setWorldY(int y) {
        worldY = y;
    }

    public int getSpriteCount() {
        return spriteCount;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public boolean isCollisionOff() {
        return !collisionOn;
    }

    public void turnOffCollision() {
        collisionOn = false;
    }
    public void turnOnCollision() {
        collisionOn = true;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public void setSpriteCount(int spriteCount) {
        this.spriteCount = spriteCount;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
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

    public int getLockActionCount() {
        return lockActionCount;
    }

    public void setLockActionCount(int lockActionCount) {
        this.lockActionCount = lockActionCount;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public BufferedImage getUp3() {
        return up3;
    }

    public BufferedImage getUp4() {
        return up4;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public void setUp3(BufferedImage up3) {
        this.up3 = up3;
    }

    public void setUp4(BufferedImage up4) {
        this.up4 = up4;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public BufferedImage getRight3() {
        return right3;
    }

    public BufferedImage getRight4() {
        return right4;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public void setRight3(BufferedImage right3) {
        this.right3 = right3;
    }

    public void setRight4(BufferedImage right4) {
        this.right4 = right4;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public BufferedImage getDown3() {
        return down3;
    }

    public BufferedImage getDown4() {
        return down4;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public void setDown3(BufferedImage down3) {
        this.down3 = down3;
    }

    public void setDown4(BufferedImage down4) {
        this.down4 = down4;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public BufferedImage getLeft3() {
        return left3;
    }

    public BufferedImage getLeft4() {
        return left4;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public void setLeft3(BufferedImage left3) {
        this.left3 = left3;
    }

    public void setLeft4(BufferedImage left4) {
        this.left4 = left4;
    }
}
