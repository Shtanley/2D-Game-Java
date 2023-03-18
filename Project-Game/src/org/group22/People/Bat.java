package org.group22.People;

import org.group22.app.GamePanel;

import java.awt.*;
import java.util.Random;

/**
 * Bat class
 * Manage bat image and bat movement
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Bat extends Enemy {
    private final int changeDirRate = 50;
    /**
     * Bat constructor
     * Set bat image and bat movement
     * @param gp    GamePanel
     */
    public Bat(GamePanel gp) {
        super(gp);
        hitBox = new Rectangle();
        name = "Bat";
        speed = 1;

        hitBox.x = 3;
        hitBox.y = 18;
        hitBox.width = 42;
        hitBox.height = 30;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        direction = "down";

        getImage();
    }

    /**
     * Set bat sprite
     */
    public void getImage() {
        up1 = Entity.setupSprite("/Enemy/bat_down_1");
        up2 = Entity.setupSprite("/Enemy/bat_down_2");
        down1 = Entity.setupSprite("/Enemy/bat_down_1");
        down2 = Entity.setupSprite("/Enemy/bat_down_2");
        left1 = Entity.setupSprite("/Enemy/bat_down_1");
        left2 = Entity.setupSprite("/Enemy/bat_down_2");
        right1 = Entity.setupSprite("/Enemy/bat_down_1");
        right1 = Entity.setupSprite("/Enemy/bat_down_2");
    }

    /**
     * Determines the bat's next direction of movement
     * Bat move uniformly randomly in all directions
     */
    public void setAction() {
        lockActionCount++;
        if(lockActionCount >= changeDirRate) {
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;

            if(n <= 25) {
                direction = "up";
            } else if(n <= 50) {
                direction = "down";
            } else if(n <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            lockActionCount = 0;
        }
    }
}
