package org.group22.People;

import org.group22.Drops.Item;
import org.group22.app.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Player class
 * Manage player image and player movement
 * @author Sameer
 */
public class Player extends Entity {
    KeyInputs keyInputs;
    public final int screenX, screenY;
    public int keyCount = 0;
    private final static int maxHealth = 200;
    private int health;
    private int points;

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
    }

    public void setPlayerValues(int x, int y, int speed, String direction) {
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        this.speed = speed;
        this.direction = direction;
    }



    public void getPlayerImage() {
        left1 = setup("/Player/priest_left1");
        left2 = setup("/Player/priest_left2");
        left3 = setup("/Player/priest_left3");
        left4 = setup("/Player/priest_left4");
        right1 = setup("/Player/priest_right1");
        right2 = setup("/Player/priest_right2");
        right3 = setup("/Player/priest_right3");
        right4 = setup("/Player/priest_right4");
        up1 = right1;
        up2 = right2;
        up3 = right3;
        up4 = right4;
        down1 = left1;
        down2 = left2;
        down3 = left3;
        down4 = left4;
    }

    public BufferedImage getFullHeart() {
        return setup("/Object/heart_full");
    }
    public BufferedImage getBlankHeart() {
        return setup("/Object/heart_blank");
    }
    public BufferedImage getHalfHeart() {
        return setup("/Object/heart_half");
    }

    /**
     * Update player position
     * Update player image
     */
    public void update() {
        // Player movement
        if(keyInputs.upPressed || keyInputs.downPressed || keyInputs.leftPressed || keyInputs.rightPressed) {
            updateDirection();

            // Collision detection
            // Tile collision
            collisionOn = false;
            gp.cCheck.checkComponent(this);
            // Object collision
            int objIndex = gp.cCheck.checkItem(this, true);
            playerInteraction(objIndex);
            // Enemy collision
            int enemyIndex = gp.cCheck.checkEntity(this, gp.enemies);
            encounter(enemyIndex != -1);

            updatePosition();

            updateSprites();
        }
    }

    /**
     * Update player's direction based on keys pressed
     */
    private void updateDirection() {
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
    }

    /**
     * Update player's x & y coordinates on the map. (i.e movement of player)
     */
    private void updatePosition() {
        if(!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

    }

    /**
     * Update player's sprites for movement
     */
    private void updateSprites() {
        spriteCount++;
        if(spriteCount > 10) {
            spriteCount = 0;
            if(spriteNum == 1)
                spriteNum = 2;
            else
                spriteNum = 1;
        }
    }

    /**
     * Check if player is colliding with an object to pick up
     * @param i index of object in object array
     */
    public void playerInteraction(int i) {
        if(i != 999) {
            Item item = gp.obj[i];
            String objName = item.name;
            switch (objName) {
                case "Key" -> {
                    keyCount++;
                    gp.obj[i] = null;
                    gp.ui.showMsg("Key acquired");
                    setPoints(item);
                }
                case "Potion" -> {
                    gp.obj[i] = null;
                    gp.ui.showMsg("Potion acquired");
                    setPoints(item);
                    if(this.health < maxHealth) {
                        setHealth(item);
                    }
                }
                case "Spikes" -> {
                    gp.obj[i] = null;
                    gp.ui.showMsg("Ouch!");
                    setPoints(item);
                    setHealth(item);
                }
                case "Door" -> {
                    if (keyCount >= gp.keysNeeded) {  // If player has collected all keys, door is unlocked (i.e. collision is turned off)
                        gp.obj[i] = null;
                        keyCount = 0;
                        setPoints(item);
                        gp.changeGameState(gp.gameState+1);
                    } else {
                        gp.ui.showMsg((gp.keysNeeded - keyCount) + " more keys required");
                    }
                    break;
                }
            }
        }
    }

    public void encounter(boolean collidedWithEnemy) {
        if(collidedWithEnemy) {
            health = 0;
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

    public boolean dead() {return health <= 0 || points < 0;}
    public int getHealth() {
        return health;
    }

    public int getMaxHealth(){ return maxHealth;}

    public int getPoints() {
        return points;
    }

    public void setPoints(Item item) {
        this.points += item.getPointAdjustment();
    }

    public void setHealth(Item item) { this.health += item.getHealthAdjustment();}
}
