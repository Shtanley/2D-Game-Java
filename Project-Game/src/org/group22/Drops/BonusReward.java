package org.group22.Drops;


/**
 * Abstract bonus reward class
 *
 * @author Michael
 */
public abstract class BonusReward extends Item{
    private final int healthAdjustment = 50;
    private final int pointAdjustment = 50;
    private static final int spawnTimer = 100;
    private static final double spawnChance = 0.3;
    public long birthTime;
    public long lifetime;

    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

    public static int getSpawnTimer() { return spawnTimer; }
    public static double getSpawnChance() {return spawnChance;}
}
