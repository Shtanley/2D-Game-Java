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
    private static final double spawnChance = 0.2;
    public long birthTime;
    public long lifetime;
    private int ticksTillDeath;

    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

    public static int getSpawnTimer() { return spawnTimer; }
    public static double getSpawnChance() {return spawnChance;}

    public int getTicksTillDeath() {
        return ticksTillDeath;
    }

    public void setTicksTillDeath(int ticksTillDeath) {
        this.ticksTillDeath = ticksTillDeath;
    }

    /**
     * Decrements ticksTillDeath by 1
     */
    public void decrementTicksTillDeath() {
        ticksTillDeath -= 1;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return name + " {" +
                "x=" + worldX + ", " +
                "y=" + worldY + ", " +
                "ticksTillDeath=" + ticksTillDeath +
                '}';
    }
}
