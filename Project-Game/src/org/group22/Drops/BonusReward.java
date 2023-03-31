package org.group22.Drops;


/**
 * Abstract bonus reward class
 *
 * @author Michael
 */
public abstract class BonusReward extends Item{
    public long birthTime;
    public long lifetime;

    public int getHealthAdjustment() { return 50;}
    public int getPointAdjustment() {
        return 50;
    }

    public static int getSpawnTimer() { return 1; } // default 100
    public static double getSpawnChance() {return 1;} // default 0.3
}
