package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Key class
 * Create key item
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Key extends Item {
    public Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
