package org.group22.Drops;


/**
 * Abstract bonus reward class
 *
 * @author Michael
 */
public abstract class BonusReward extends Item{
    private final int healthAdjustment = 50;
    private final int pointAdjustment = 50;
    private int ticksTillDeath;

    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

    public int getTicksTillDeath() {
        return ticksTillDeath;
    }

    public void setTicksTillDeath(int ticksTillDeath) {
        this.ticksTillDeath = ticksTillDeath;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "BonusReward{" +
                "ticksTillDeath=" + ticksTillDeath +
                '}';
    }
}
