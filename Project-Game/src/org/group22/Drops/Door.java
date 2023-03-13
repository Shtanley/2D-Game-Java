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
    // public BufferedImage closedGate, openedGate;
    // Boolean open = false;

    /**
     * Door class
     * Create door item
     */
    public Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    // /**
    //  * Opens the gate when player has the required number of keys.
    //  * Then it teleports the player to the next stage after colliding with it.
    //  */
    // public void update(GamePanel gp) {
    //     if (open == false) {
    //         open = true;
    //         image = openedGate;
    //     }
    //     else {  // Uncomment after implementing nextMap()
    //         // gp.mapManager.nextMap();

    //     }
    // }

    // public Door(Location loc) {
    //     setLoc(loc);
    //     setItem(null);
    //     System.out.println("Created " + this);
    // }

    // public String toString() {
    //     return "Door at " + loc + " containing item: \"" + item + "\" and character: \"" + charHere + "\"";
    // }
}
