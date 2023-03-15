package org.group22.People;

import org.group22.GameMap.*;
import org.group22.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

public abstract class Entity {
    GamePanel gp;
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
    public String name;
    public int lockActionCount;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {};

    public void update() {
        setAction();

        collisionOn = false;
        gp.cCheck.checkComponent(this);

        // Collision detection
        collisionOn = false;
        gp.cCheck.checkComponent(this);
        gp.cCheck.checkEntity(this, gp.bat);

        if(!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCount++;
        if(spriteCount > 10) {
            spriteCount = 0;
            if(spriteNum == 1)
                spriteNum = 2;
            else
                spriteNum = 1;
        }
    };

    public BufferedImage scaleImg(BufferedImage original, int width, int height) {
        BufferedImage newImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return newImg;
    }

    public BufferedImage setup(String imgPath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imgPath + ".png")));
            image = scaleImg(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        // Calculate x and y position of tile on screen
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Draw tile if it is on screen to save resources
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1)
                        image = up1;
                    else
                        image = up2;
                }
                case "down" -> {
                    if (spriteNum == 1)
                        image = down1;
                    else
                        image = down2;
                }
                case "left" -> {
                    if (spriteNum == 1)
                        image = left1;
                    else
                        image = left2;
                }
                case "right" -> {
                    if (spriteNum == 1)
                        image = right1;
                    else
                        image = right2;
                }
            }
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

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
