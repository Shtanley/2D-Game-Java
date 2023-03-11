package org.group22.People;

import org.group22.app.*;

import java.awt.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Player class
 * Manage player image and player movement
 * @author Sameer
 */
public class Player extends Entity {
    GamePanel gp;
    KeyInputs keyInputs;
    public final int screenX, screenY;
    int hasKeys = 0;

    /**
     * Player constructor
     * Set default values
     * Get player image
     * @param gp
     * @param keyIn
     */
    public Player(GamePanel gp, KeyInputs keyIn) {
        this.gp = gp;
        this.keyInputs = keyIn;
        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 32;
        hitBox.height = 32;
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }


    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update player position
     * Update player image
     */
    public void update() {
        if(keyInputs.upPressed || keyInputs.downPressed || keyInputs.leftPressed || keyInputs.rightPressed) {
            if (keyInputs.upPressed) {
                direction = "up";
            }
            if (keyInputs.downPressed) {
                direction = "down";
            }
            if (keyInputs.leftPressed) {
                direction = "left";
            }
            if (keyInputs.rightPressed) {
                direction = "right";
            }

            // Collision detection
            collisionOn = false;
            gp.cCheck.checkComponent(this);
            int objIndex = gp.cCheck.checkItem(this, true);
            pickupItem(objIndex);
            if(!collisionOn) {
                switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
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
        }
    }

    /**
     * Check if player is colliding with an object to pick up
     * @param i
     * @return
     */
    public void pickupItem(int i) {
        if(i != 999) {
            String objName = gp.obj[i].name;
            switch(objName) {
                case "Key":
                    hasKeys++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKeys);
                    break;
                case "Potion":
                    if(hasKeys > 0) {
                        gp.obj[i] = null;
                    }
                    break;
            }
        }
    }

    /**
     * Draw player image
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        // Easter egg idea: player image is a white square
        // g.setColor(Color.WHITE);
        // g.fillRect(worldX, worldY, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else
                    image = right2;
                break;
        }

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
