package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Potion class
 * Create potion item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Potion extends BonusReward {
    private static final int despawnTimer = 100;

    public Potion(int x, int y, long time) {
        name = "Potion";
        image = Item.setupSprite("/Object/newpotion");
        worldX = x;
        worldY = y;
        setTicksTillDeath(despawnTimer);
        birthTime = time;
        lifetime = 4;
    }
}
