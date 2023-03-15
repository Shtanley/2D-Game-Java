package org.group22.People;

import org.group22.Drops.Door;
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
        getPlayerImage();

        System.out.println("Creating Player");
        this.health = maxHealth;
        this.points = 0;
        itemsCollected = new ArrayList<>();
    }
    
    public void setPlayerValues(int x, int y, int speed, String direction) {
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        this.speed = speed;
        this.direction = direction;
    }


    public void getPlayerImage() {
        up1 = setup("/Player/boy_up_1");
        up2 = setup("/Player/boy_up_2");
        down1 = setup("/Player/boy_down_1");
        down2 = setup("/Player/boy_down_2");
        left1 = setup("/Player/boy_left_1");
        left2 = setup("/Player/boy_left_2");
        right1 = setup("/Player/boy_right_1");
        right2 = setup("/Player/boy_right_2");
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
            // Tile collision
            collisionOn = false;
            gp.cCheck.checkComponent(this);
            // Object collision
            int objIndex = gp.cCheck.checkItem(this, true);
            pickupItem(objIndex);
            // Enemy collision
            int enemyIndex = gp.cCheck.checkEntity(this, gp.bat);
            encounter(enemyIndex != -1);
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
        }
    }

    /**
     * Check if player is colliding with an object to pick up
     * @param i index of object in object array
     */
    public void pickupItem(int i) {
        if(i != 999) {
            Item item = gp.obj[i];
            String objName = item.name;
            switch (objName) {
                case "Key" -> {

                    keyCount++;
                    gp.obj[i] = null;
                    gp.ui.showMsg("Key acquired");
                    setHealth(item);
                    setPoints(item);
                }
                case "Potion" -> {
                    gp.obj[i] = null;
                    gp.ui.showMsg("Potion acquired");
                    setPoints(item);
                    setHealth(item);
                }
                case "Spikes" -> {
                    gp.obj[i] = null;
                    gp.ui.showMsg("Ouch!");
                    setPoints(item);
                    setHealth(item);
                }
                case "Door" -> {
                    if (keyCount == gp.keysNeeded) {  // If player has collected all keys, door is unlocked collison is turned off
                        gp.obj[i] = null;
                        keyCount = 0;
                        setPoints(item);
                        gp.changeGameState(gp.gameState+1);
                    } else {
                        gp.ui.showMsg((gp.keysNeeded - keyCount) + " more keys required");
                    }
                }
            }
        }
    }

    public void encounter(boolean collidedWithEnemy) {
        if(collidedWithEnemy) {
            gp.gameState = gp.endState;
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

    public boolean dead() {return this.health <= 0;}
    public int getHealth() {
        return health;
    }

    public void shiftHealth(int deltaHealth) {this.health += deltaHealth;}

    public int getPoints() {
        return points;
    }

    public void setPoints(Item item) {
        this.points += item.getPointAdjustment();
    }

    public void setHealth(Item item) { this.health += item.getHealthAdjustment();}
}
