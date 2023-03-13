package org.group22.Drops;

public class BonusReward extends Item{
    private int ticksTillDeath;

    public BonusReward(int ticksTillDeath){
        super();
        this.ticksTillDeath = ticksTillDeath;
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
