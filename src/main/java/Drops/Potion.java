package Drops;

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
    private static final int potionHealth = 50;
    private static final int potionPoints = 50;
    private static int potionSpawnTimer = 100;
    private static double potionSpawnChance = 0.5;
    private static long potionLifetime = 5;
    public Potion(int x, int y, long time) {
        setName("Potion");
        setImage(setupSprite("/Object/newpotion"));
        setWorldX(x);
        setWorldY(y);
        setBirthTime(time);
        setLifetime(Potion.getPotionLifetime());
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
        return potionHealth;
    }
    public int getPointAdjustment() {
        return potionPoints;
    }
    public static int getSpawnTimer() {
        return potionSpawnTimer;
    }
    public static double getSpawnChance() {
        return potionSpawnChance;
    }

    public static void setPotionSpawnTimer(int potionSpawnTimer) {
        Potion.potionSpawnTimer = potionSpawnTimer;
    }

    public static void setPotionSpawnChance(double potionSpawnChance) {
        Potion.potionSpawnChance = potionSpawnChance;
    }

    public static long getPotionLifetime() {
        return potionLifetime;
    }

    public static void setPotionLifetime(long potionLifetime) {
        Potion.potionLifetime = potionLifetime;
    }
}
