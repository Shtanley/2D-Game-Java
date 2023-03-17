package org.group22.Drops;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
        name = "Key";
        image = Item.setupSprite("/Object/newkey");
        worldX = x;
        worldY = y;
    }

    /**
     * Static method for getting key sprite
     *
     * @return a BufferedImage key sprite
     */
    public static BufferedImage getImage(){
        return Item.setupSprite("/Object/newkey");
    }

}
