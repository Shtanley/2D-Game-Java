package org.group22.Drops;

/**
 * Potion class
 * Create potion item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Potion extends BonusReward {
    public Potion(int x, int y, long time) {
        name = "Potion";
        image = Item.setupSprite("/Object/newpotion");
        worldX = x;
        worldY = y;
        birthTime = time;
        lifetime = 5;
    }
}
