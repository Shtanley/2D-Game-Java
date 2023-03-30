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
    /**
     * Bat constructor
     * Set bat image and bat movement
     * @param gp    GamePanel
     */
    public Bat(GamePanel gp) {
        super(gp);
        setHitBox(new Rectangle(3, 18, 42, 30));
        setName("Bat");
        setSpeed(1);

        setHitBoxDefaultX(getHitBox().x);
        setHitBoxDefaultY(getHitBox().y);

        setDirection("down");

        getImage();
    }

    /**
     * Set bat sprite
     */
    public void getImage() {
        setUp1(Entity.setupSprite("/Enemy/bat_down_1"));
        setUp2(Entity.setupSprite("/Enemy/bat_down_2"));
        setDown1(Entity.setupSprite("/Enemy/bat_down_1"));
        setDown2(Entity.setupSprite("/Enemy/bat_down_2"));
        setLeft1(Entity.setupSprite("/Enemy/bat_down_1"));
        setLeft2(Entity.setupSprite("/Enemy/bat_down_2"));
        setRight1(Entity.setupSprite("/Enemy/bat_down_1"));
        setRight2(Entity.setupSprite("/Enemy/bat_down_2"));
    }

    /**
     * Determines the bat's next direction of movement
     * Bat move uniformly randomly in all directions
     */
    public void setAction() {
        setLockActionCount(getLockActionCount() + 1);
        int changeDirRate = 50;
        if(getLockActionCount() >= changeDirRate) {
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;

            if(n <= 25) {
                setDirection("up");
            } else if(n <= 50) {
                setDirection("down");
            } else if(n <= 75) {
                setDirection("left");
            } else {
                setDirection("right");
            }
            setLockActionCount(0);
        }
    }
}
