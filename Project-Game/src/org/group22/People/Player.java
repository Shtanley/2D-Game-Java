package org.group22.People;

import org.group22.Drops.Item;
import org.group22.Drops.Key;
import org.group22.Drops.Potion;
import org.group22.app.*;

import java.awt.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Player class
 * Manage player image and player movement
 * @author Sameer
 */
public class Player extends Entity {
    KeyInputs keyInputs;
    public final int screenX, screenY;
    public int keyCount = 0;
    private final static int maxHealth = 100;
    private int health;
    private int points;
    private ArrayList<Item> itemsCollected;


    /**
     * Player constructor
     * Set default values
     * Get player image
     * @param gp    GamePanel
     * @param keyIn KeyInputs
     */
    public Player(GamePanel gp, KeyInputs keyIn) {
        super(gp);  // call constructor of super class

        this.keyInputs = keyIn;
        // Screen position of player
        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        // Hitbox position
        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        // Hitbox size
        hitBox.width = 22;
        hitBox.height = 25;
        setDefaultValues();
        getPlayerImage();

        System.out.println("Creating Player");
        this.health = maxHealth;
        this.points = 0;
        itemsCollected = new ArrayList<>();
    }
    
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 8;
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
        // Player movement
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
     * @param i index of object in object array
     * @return  true if player is colliding with an object
     */
    public void pickupItem(int i) {
        if(i != 999) {
            String objName = gp.obj[i].name;
            switch(objName) {
                case "Key":
                    keyCount++;
                    gp.obj[i] = null;
                    gp.ui.showMsg("Key acquired");
                    setPoints(this.getPoints() + Key.getHealthAdjustment());
                    break;
                case "Potion":
                    gp.obj[i] = null;
                    setPoints(this.getPoints() + Potion.getHealthAdjustment());
                    break;
                case "Door":
                    if(keyCount == 7) {  // If player has collected all keys, door is unlocked collison is turned off
                        gp.obj[i] = null;
                        keyCount = 0;
                        gp.ui.gameOver = true;
                    }
                    else {
                        gp.ui.showMsg((7 - keyCount) + " more keys required");
                    }
                    break;
            }
        }
    }

    /**
     * Draw player image
     * @param g2d   Graphics2D object
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

    public boolean dead() {return this.health <= 0;}
    public int getHealth() {
        return health;
    }

    public void shiftHealth(int deltaHealth) {this.health += deltaHealth;}

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
