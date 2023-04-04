package main.java.Drops;

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

    public abstract int getHealthAdjustment();
    public abstract int getPointAdjustment();
}
