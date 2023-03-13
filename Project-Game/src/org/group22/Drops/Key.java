package org.group22.Drops;

import org.group22.app.GamePanel;

import org.group22.GameMap.Location;
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

    public Key(Location loc) {
        super(loc);
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Uncomment after implementing door and map objectives
    // public void update(GamePanel gp) {
    //     gp.player.keyCount++;
    //     if (gp.player.keyCount == gp.cFactory.getMap().keyNum) {
    //         gp.cFactory.getObject(gp.cFactory.getMap().gateIndex).update(gp);
    //         sound.play(8);
    //         gp.uiManager.showMessage("GATE OPENED");
    //     }
    //     else {
    //         int keysLeft = gp.cFactory.getMap().keyNum - gp.player.keyCount;
    //         if (keysLeft != 1) {
    //             gp.uiManager.showMessage("NEED " + keysLeft + " MORE KEYS");
    //         }
    //         else {
    //             gp.uiManager.showMessage("NEED " + keysLeft + " MORE KEY");
    //         }

    //     }
    //     sound.play(2);
    // }
}
