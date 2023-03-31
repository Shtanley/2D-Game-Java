package org.group22.Drops;

import java.awt.image.BufferedImage;

/**
 * Door class
 * Create door item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Door extends Item {

    public Door(int x, int y) {
        setName("Door");
        setImage(Item.setupSprite("/Object/newdoor"));
        setWorldX(x);
        setWorldY(y);
        setCollision(true);
    }

    /**
     * Static method for getting door sprite
     *
     * @return a BufferedImage door sprite
     */
    public static BufferedImage getSprite(){
        return Item.setupSprite("/Object/newdoor");
    }

    public int getHealthAdjustment() {
        return 0;
    }
    public int getPointAdjustment() {
        return 50;
    }

}
