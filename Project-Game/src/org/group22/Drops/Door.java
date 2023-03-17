package org.group22.Drops;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Door class
 * Create door item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Door extends Item {
    private final int healthAdjustment = 0;
    private final int pointAdjustment = 50;

    public Door(int x, int y) {
        name = "Door";
        image = Item.setupSprite("/Object/newdoor");
        worldX = x;
        worldY = y;
        collision = true;
    }

    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

}
