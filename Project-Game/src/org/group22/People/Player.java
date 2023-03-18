package org.group22.People;

import org.group22.Drops.Item;
import org.group22.app.*;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
        resetPlayer();
    }

    /**
     * Set the player's in game values
     *
     * @param x         Player's x coordinate
     * @param y         Player's y coordinate
     * @param speed     Player's speed
     * @param direction Direction the player is facing
     */
    public void setPlayerValues(int x, int y, int speed, String direction) {
        worldX = gp.tileSize * x;
        worldY = gp.tileSize * y;
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Reset the player's health, points, and keys
     */
    public void resetPlayer(){
        this.health = maxHealth;
        this.points = 0;
        this.keyCount = 0;
    }


    /**
     * Set up player sprites
     */
    public void getPlayerImage() {
        left1 = setupSprite("/Player/priest_left1");
        left2 = setupSprite("/Player/priest_left2");
        left3 = setupSprite("/Player/priest_left3");
        left4 = setupSprite("/Player/priest_left4");
        right1 = setupSprite("/Player/priest_right1");
        right2 = setupSprite("/Player/priest_right2");
        right3 = setupSprite("/Player/priest_right3");
        right4 = setupSprite("/Player/priest_right4");
        up1 = right1;
        up2 = right2;
        up3 = right3;
        up4 = right4;
        down1 = left1;
        down2 = left2;
        down3 = left3;
        down4 = left4;
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
            Item obj = gp.cCheck.checkItem(this, true);
            if(obj != null) {
                pickupItem(obj);
            }
            // Enemy collision
            int enemyIndex = gp.cCheck.checkEntity(this, gp.enemies);
            if(enemyIndex != -1) {
                encounter();
            }
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
     * Check if player is colliding with an item
     *
     * @param item the item being checked for
     */
    public void pickupItem(Item item) {
        boolean pickedUp = true;
        String objName = item.name;
        switch (objName) {
            case "Key" -> {
                keyCount++;
                gp.ui.showMsg("Key acquired");
                setPoints(item);
            }
            case "Potion" -> {
                gp.ui.showMsg("Potion acquired");
                setPoints(item);
                if(this.health < maxHealth) {
                    setHealth(item);
                }
            }
            case "Spikes" -> {
                gp.ui.showMsg("Ouch!");
                setPoints(item);
                setHealth(item);
            }
            case "Door" -> {
                if (keyCount >= gp.keysNeeded) {  // If player has collected all keys, door is unlocked (i.e. collision is turned off)
                    keyCount = 0;
                    setPoints(item);
                    gp.changeGameState(gp.gameState+1);
                } else {
                    gp.ui.showMsg((gp.keysNeeded - keyCount) + " more keys required");
                    pickedUp = false;
                }
            }
        }
        // If successful, delete the item from the game, wherever it is
        synchronized (gp.lock1) {
            if (pickedUp) {
                // Traverse obj
                for (int i = 0; i < gp.obj.length; i++) {
                    if (item.equals(gp.obj[i])) {
                        gp.obj[i] = null;
                    }
                }
                gp.tempItems.remove(item);
            }
        }
    }

    /**
     * Method for when the player collides with an enemy
     * Sets the players health to zero and ends the game
     */
    public void encounter() {
        health = 0;
        gp.gameState = gp.endState;
    }

    /**
     * Draw player image
     *
     * @param g2d   Graphics2D object
     */
    public void draw(Graphics2D g2d) {
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

    /**
     * Get the appropriate heart sprite depending on the player's health
     *
     * @param num   number indicating the state of the player's health
     * @return a BufferedImage heart sprite
     */
    public BufferedImage getHeartSprite(int num){
        BufferedImage result = null;
        switch(num){
            case(-1) -> result = Entity.setupSprite("/Object/heart_empty");
            case(0) -> result = Entity.setupSprite("/Object/heart_0");
            case(1) -> result = Entity.setupSprite("/Object/heart_1");
            case(2) -> result = Entity.setupSprite("/Object/heart_2");
            case(3) -> result = Entity.setupSprite("/Object/heart_3");
            case(4) -> result = Entity.setupSprite("/Object/heart_4");
            case(5) -> result = Entity.setupSprite("/Object/heart_5");
            case(6) -> result = Entity.setupSprite("/Object/heart_6");
            case(7) -> result = Entity.setupSprite("/Object/heart_7");
            case(8) -> result = Entity.setupSprite("/Object/heart_8");
            case(9) -> result = Entity.setupSprite("/Object/heart_9");
            case(10) -> result = Entity.setupSprite("/Object/heart_full");
            case(11) -> result = Entity.setupSprite("/Object/heart_");
        }
        assert(result != null);
        return result;
    }

    /**
     * @return whether the player is dead or not
     */
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

    public void setHealth(Item item) {
        health += item.getHealthAdjustment();
        if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void setHealth(int deltaHealth) {
        health += deltaHealth;
        if(health > maxHealth){
            health = maxHealth;
        }
    }
}
