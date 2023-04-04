package main.java.Drops;

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
        setImage(setupSprite("/Object/newdoor"));
        setWorldX(x);
        setWorldY(y);
    }

    /**
     * Static method for getting door sprite
     *
     * @return a BufferedImage door sprite
     */
    public static BufferedImage getSprite(){
        return setupSprite("/Object/newdoor");
    }

    public int getHealthAdjustment() {
        return 0;
    }
    public int getPointAdjustment() {
        return 50;
    }

}
