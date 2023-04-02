package org.group22.Drops;


/**
 * Abstract bonus reward class
 *
 * @author Michael
 */
public abstract class BonusReward extends Item{

    private long birthTime;
    private long lifetime;

    public long getBirthTime() {
        return birthTime;
    }
    public long getLifetime() {
        return lifetime;
    }

    protected void setBirthTime(long birthTime) {
        this.birthTime = birthTime;
    }
    protected void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public int getHealthAdjustment() { return 50;}
    public int getPointAdjustment() {
        return 50;
    }

    public static int getSpawnTimer() { return 1; } // default 100
    public static double getSpawnChance() {return 1;} // default 0.3
}
