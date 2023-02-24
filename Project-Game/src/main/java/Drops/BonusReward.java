package Drops;

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
}
