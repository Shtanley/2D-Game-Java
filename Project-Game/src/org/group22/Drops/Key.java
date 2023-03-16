package org.group22.Drops;

import org.group22.app.GamePanel;

import org.group22.GameMap.Location;
import javax.imageio.ImageIO;
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
    public Key() {
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/newkey.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
