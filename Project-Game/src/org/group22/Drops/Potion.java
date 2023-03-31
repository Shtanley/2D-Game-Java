package org.group22.Drops;

import java.awt.image.BufferedImage;

/**
 * Potion class
 * Create potion item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Potion extends BonusReward {
    public Potion(int x, int y, long time) {
        setName("Potion");
        setImage(Item.setupSprite("/Object/newpotion"));
        setWorldX(x);
        setWorldY(y);
        birthTime = time;
        lifetime = 10; // default 5
    }

    /**
     * Static method for getting potion sprite
     *
     * @return a BufferedImage potion sprite
     */
    public static BufferedImage getSprite(){
        return Item.setupSprite("/Object/newpotion");
    }
}
