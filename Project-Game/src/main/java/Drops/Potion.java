package main.java.Drops;

import main.java.app.GameSettings;

import java.awt.image.BufferedImage;

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
        setName("Potion");
        setImage(setupSprite("/Object/newpotion"));
        setWorldX(x);
        setWorldY(y);
        setBirthTime(time);
        setLifetime(GameSettings.getPotionLifetime());
    }

    /**
     * Static method for getting potion sprite
     *
     * @return a BufferedImage potion sprite
     */
    public static BufferedImage getSprite(){
        return setupSprite("/Object/newpotion");
    }
    public int getHealthAdjustment() {
        return GameSettings.getPotionHealth();
    }
    public int getPointAdjustment() {
        return GameSettings.getPotionPoints();
    }
    public static int getSpawnTimer() {
        return GameSettings.getPotionSpawnTimer();
    }
    public static double getSpawnChance() {
        return GameSettings.getPotionSpawnChance();
    }
}
