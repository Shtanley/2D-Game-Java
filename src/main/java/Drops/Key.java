package Drops;

import java.awt.image.BufferedImage;

/**
 * Key class
 * Create key item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Key extends Reward {
    public Key(int x, int y) {
        setName("Key");
        setImage(setupSprite("/Object/newkey"));
        setWorldX(x);
        setWorldY(y);
    }

    /**
     * Static method for getting key sprite
     *
     * @return a BufferedImage key sprite
     */
    public static BufferedImage getSprite(){
        return setupSprite("/Object/newkey");
    }

}
