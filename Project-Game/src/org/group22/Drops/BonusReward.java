package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;

public abstract class BonusReward extends Item{
    private final int healthAdjustment = 10;
    private final int pointAdjustment = 10;
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
