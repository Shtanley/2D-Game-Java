package org.group22.People;

import org.group22.app.GamePanel;

import java.awt.*;
import java.util.Random;

/**
 * Slime class
 * Manage slime image and slime movement
 * Slimes have a tracking movement pattern
 *
 * @author Michael
 */

public class Slime extends Enemy {
    /**
     * Slime constructor
     * Set slime image and slime movement
     *
     * @param gp    GamePanel
     */
    public Slime(GamePanel gp) {
        super(gp);
        setHitBox(new Rectangle(3, 18, 42, 30));
        setName("Slime");
        setSpeed(1);

        setHitBoxDefaultX(getHitBox().x);
        setHitBoxDefaultY(getHitBox().y);

        setDirection("down");

        getImage();
    }

    /**
     * Set slime sprite
     * Randomly selects the slime's sprite between green and red
     */
    public void getImage() {
        Random rand = new Random();
        double p = rand.nextDouble();

        // spawn green slime
        if(p < 0.5) {
            setUp1(Entity.setupSprite("/Enemy/greenslime_down_1"));
            setUp2(Entity.setupSprite("/Enemy/greenslime_down_2"));
            setDown1(Entity.setupSprite("/Enemy/greenslime_down_1"));
            setDown2(Entity.setupSprite("/Enemy/greenslime_down_2"));
            setLeft1(Entity.setupSprite("/Enemy/greenslime_down_1"));
            setLeft2(Entity.setupSprite("/Enemy/greenslime_down_2"));
            setRight1(Entity.setupSprite("/Enemy/greenslime_down_1"));
            setRight2(Entity.setupSprite("/Enemy/greenslime_down_2"));

        // spawn red slime
        } else {
            setUp1(Entity.setupSprite("/Enemy/redslime_down_1"));
            setUp2(Entity.setupSprite("/Enemy/redslime_down_2"));
            setDown1(Entity.setupSprite("/Enemy/redslime_down_1"));
            setDown2(Entity.setupSprite("/Enemy/redslime_down_2"));
            setLeft1(Entity.setupSprite("/Enemy/redslime_down_1"));
            setLeft2(Entity.setupSprite("/Enemy/redslime_down_2"));
            setRight1(Entity.setupSprite("/Enemy/redslime_down_1"));
            setRight2(Entity.setupSprite("/Enemy/redslime_down_2"));
        }
    }

    /**
     * Set slime movement
     * Slimes follow a tracking movement pattern
     * i.e. slimes will always attempt to move in a direction that  brings them closest to the player
     */
    public void setAction() {
        // The slime can either move closer to the player in the x-direction or the y-direction
        // The chances of choosing x vs y is weighted by the ratio of the distances in either direction
        // This way, the slime will move in an approximately straight line directly towards the player.
        int deltaX = Math.abs(gp.player.getWorldX() - getWorldX());
        int deltaY = Math.abs(gp.player.getWorldY() - getWorldY());
        double ratio = (double)deltaX/(deltaY + deltaX); // Manhattan metric (straight line)

        Random rand = new Random();
        double p = rand.nextDouble();
        if(p < ratio) {
            // Move in the x direction
            if(gp.player.getWorldX() < getWorldX()){
                setDirection("left");
            } else {
                setDirection("right");
            }
        } else {
            // Move in the y direction
            if(gp.player.getWorldY() < getWorldY()){
                setDirection("up");
            } else {
                setDirection("down");
            }
        }

    }
}
