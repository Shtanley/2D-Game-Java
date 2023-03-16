package org.group22.Drops;


/**
 * Abstract reward class
 *
 * @author Michael
 */
public abstract class Reward extends Item{
    private final int healthAdjustment = 0;
    private final int pointAdjustment = 25;
    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

}
