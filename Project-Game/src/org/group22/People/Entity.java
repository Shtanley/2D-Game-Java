package org.group22.People;

import org.group22.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;

public abstract class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, up4, right1, right2, right3, right4, down1, down2, down3, down4, left1, left2, left3, left4;
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

    public abstract void update();

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
//            image = scaleImg(image, gp.tileSize, gp.tileSize);
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
                    else if (spriteNum == 2)
                        image = up2;
                    else if (spriteNum == 3)
                        image = up3;
                    else
                        image = up4;
                }
                case "down" -> {
                    if (spriteNum == 1)
                        image = down1;
                    else if (spriteNum == 2)
                        image = down2;
                    else if (spriteNum == 3)
                        image = down3;
                    else
                        image = down4;
                }
                case "left" -> {
                    if (spriteNum == 1)
                        image = left1;
                    else if (spriteNum == 2)
                        image = left2;
                    else if (spriteNum == 3)
                        image = left3;
                    else
                        image = left4;
                }
                case "right" -> {
                    if (spriteNum == 1)
                        image = right1;
                    else if (spriteNum == 2)
                        image = right2;
                    else if (spriteNum == 3)
                        image = right3;
                    else
                        image = right4;
                }
            }
            g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void setWorldX(int x){
        worldX = x;
    }

    public void setWorldY(int y) {
        worldY = y;
    }
}
