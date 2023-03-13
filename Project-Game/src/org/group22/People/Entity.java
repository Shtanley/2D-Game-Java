package org.group22.People;

import org.group22.GameMap.Location;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * CharacterFactory class
 * Manage character image and character movement
 * @author Sameer
 */
public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, right1, right2, down1, down2, left1, left2;
    public String direction;

    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;
}