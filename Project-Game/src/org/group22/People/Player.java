package org.group22.People;

import org.group22.Drops.Item;
import org.group22.app.*;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Player class
 * Manage player image and player movement
 *  @author Dina
 *  @author Michael
 *  @author Sameer
 *
 */
public class Player extends Entity {
    private final KeyInputs keyInputs;
    private final int screenX, screenY;
    private int keyCount = 0;
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
        screenX = GameSettings.getScreenWidth() / 2 - GameSettings.getTileSize() / 2;
        screenY = GameSettings.getScreenHeight() / 2 - GameSettings.getTileSize() / 2;
        // Hit box position + size
        setHitBox(new Rectangle(8, 16, 22, 25));

        setHitBoxDefaultX(getHitBox().x);
        setHitBoxDefaultY(getHitBox().y);

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
        setWorldX(GameSettings.getTileSize() * x);
        setWorldY(GameSettings.getTileSize() * y);

        setDirection(direction);
        setSpeed(speed);
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
        setLeft1(setupSprite("/Player/priest_left1"));
        setLeft2(setupSprite("/Player/priest_left2"));
        setLeft3(setupSprite("/Player/priest_left3"));
        setLeft4(setupSprite("/Player/priest_left4"));
        setRight1(setupSprite("/Player/priest_right1"));
        setRight2(setupSprite("/Player/priest_right2"));
        setRight3(setupSprite("/Player/priest_right3"));
        setRight4(setupSprite("/Player/priest_right4"));
        setUp1(getRight1());
        setUp2(getRight2());
        setUp3(getRight3());
        setUp4(getRight4());
        setDown1(getLeft1());
        setDown2(getLeft2());
        setDown3(getLeft3());
        setDown4(getLeft4());
    }

    /**
     * Update player position
     * Update player image
     */
    public void update() {
        // Player movement
        if(keyInputs.isUpPressed() || keyInputs.isDownPressed() || keyInputs.isLeftPressed() || keyInputs.isRightPressed()) {
            updateDirection();

            // Collision detection
            // Tile collision
            turnOffCollision();
            gp.cCheck.checkComponent(this);
            // Object collision
            Item obj = gp.cCheck.checkItem(this, true);
            if(obj != null) {
                playerInteraction(obj);
            }

            // Enemy collision
            int enemyIndex = gp.cCheck.checkEntity(this, gp.enemies);
            if(enemyIndex != -1) {
                encounter();
            }

            updatePosition();
            updateSprites();
        }
    }

    /**
     * Update player's direction based on keys pressed
     */
    private void updateDirection() {
        if (keyInputs.isUpPressed()) {
            setDirection("up");
        }
        if (keyInputs.isDownPressed()) {
            setDirection("down");
        }
        if (keyInputs.isLeftPressed()) {
            setDirection("left");
        }
        if (keyInputs.isRightPressed()) {
            setDirection("right");
        }
    }

    /**
     * Update player's x & y coordinates on the map. (i.e. movement of player)
     */
    private void updatePosition() {
        if(isCollisionOff()) {
            switch (getDirection()) {
                case "up" -> setWorldY(getWorldY() - getSpeed());
                case "down" -> setWorldY(getWorldY() + getSpeed());
                case "left" -> setWorldX(getWorldX() - getSpeed());
                case "right" -> setWorldX(getWorldX() + getSpeed());
            }
        }
    }

    /**
     * Update player's sprites for movement
     */
    private void updateSprites() {
        setSpriteCount(getSpriteCount() + 1);
        if(getSpriteCount() > 10) {
            setSpriteCount(0);
            if(getSpriteNum() == 1)
                setSpriteNum(2);
            else
                setSpriteNum(1);
        }
    }

    /**
     * Check if player is colliding with an item
     *
     * @param item the item being checked for
     */
    public void playerInteraction(Item item) {
        boolean pickedUp = true;
        String objName = item.getName();
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
                if (keyCount >= GameSettings.getKeysNeeded()) {  // If player has collected all keys, door is unlocked (i.e. collision is turned off)
                    keyCount = 0;
                    setPoints(item);
                    gp.changeGameState(gp.getGameState()+1);
                } else {
                    gp.ui.showMsg((GameSettings.getKeysNeeded() - keyCount) + " more keys required");
                    pickedUp = false;
                }
            }
        }
        // If successful, delete the item from the game, wherever it is
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

    /**
     * Method for when the player collides with an enemy
     * Sets the players health to zero and ends the game
     */
    public void encounter() {
        health = 0;
        gp.changeGameState(GamePanel.endState);
    }

    /**
     * Draw player image
     *
     * @param g2d   Graphics2D object
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        switch (getDirection()) {
            case "up" -> {
                if (getSpriteNum() == 1)
                    image = getUp1();
                else
                    image = getUp2();
            }
            case "down" -> {
                if (getSpriteNum() == 1)
                    image = getDown1();
                else
                    image = getDown2();
            }
            case "left" -> {
                if (getSpriteNum() == 1)
                    image = getLeft1();
                else
                    image = getLeft2();
            }
            case "right" -> {
                if (getSpriteNum() == 1)
                    image = getRight1();
                else
                    image = getRight2();
            }
        }
        g2d.drawImage(image, screenX, screenY, GameSettings.getTileSize(), GameSettings.getTileSize(), null);
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
     * Static method for getting Player sprite
     *
     * @return a BufferedImage Player sprite
     */
    public static BufferedImage getSprite(){
        return setupSprite("/Player/priest_right1");
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

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getKeyCount() {
        return keyCount;
    }
}
