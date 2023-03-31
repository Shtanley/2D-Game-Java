package org.group22.People;

import org.group22.app.GamePanel;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.abs;

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
        hitBox = new Rectangle();
        name = "Slime";
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
     * Set slime sprite
     * Randomly selects the slime's sprite between green and red
     */
    public void getImage() {
        Random rand = new Random();
        double p = rand.nextDouble();
        if(p < 0.5) {
            up1 = Entity.setupSprite("/Enemy/greenslime_down_1");
            up2 = Entity.setupSprite("/Enemy/greenslime_down_2");
            down1 = Entity.setupSprite("/Enemy/greenslime_down_1");
            down2 = Entity.setupSprite("/Enemy/greenslime_down_2");
            left1 = Entity.setupSprite("/Enemy/greenslime_down_1");
            left2 = Entity.setupSprite("/Enemy/greenslime_down_2");
            right1 = Entity.setupSprite("/Enemy/greenslime_down_1");
            right2 = Entity.setupSprite("/Enemy/greenslime_down_2");
        } else {
            up1 = Entity.setupSprite("/Enemy/redslime_down_1");
            up2 = Entity.setupSprite("/Enemy/redslime_down_2");
            down1 = Entity.setupSprite("/Enemy/redslime_down_1");
            down2 = Entity.setupSprite("/Enemy/redslime_down_2");
            left1 = Entity.setupSprite("/Enemy/redslime_down_1");
            left2 = Entity.setupSprite("/Enemy/redslime_down_2");
            right1 = Entity.setupSprite("/Enemy/redslime_down_1");
            right2 = Entity.setupSprite("/Enemy/redslime_down_2");
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
        int deltax = Math.abs(gp.player.worldX - this.worldX);
        int deltay = Math.abs(gp.player.worldY - this.worldY);
        double ratio = (double)deltax/(deltay + deltax); // Manhattan metric (straight line)
//        double ratio = (double)deltax*deltax/(deltay*deltay + deltax*deltax); // Euclidean metric (curved)

        Random rand = new Random();
        double p = rand.nextDouble();
        if(p < ratio) {
            // Move in the x direction
            if(gp.player.worldX < this.worldX){
                direction = "left";
            } else {
                direction = "right";
            }
        } else {
            // Move in the y direction
            if(gp.player.worldY < this.worldY){
                direction = "up";
            } else {
                direction = "down";
            }
        }

    }
}
