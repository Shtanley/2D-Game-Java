package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Potion class
 * Create potion item
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Potion extends Item {
    public Potion() {
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/potion_red.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setHealthAdjustment(10);
    }
}
