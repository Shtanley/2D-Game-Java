package org.group22.Drops;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Door class
 * Create door item
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Door extends Item {
    private final int healthAdjustment = 0;
    private final int pointAdjustment = 50;

    public Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

}
