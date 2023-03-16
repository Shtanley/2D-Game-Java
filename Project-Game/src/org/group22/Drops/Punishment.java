package org.group22.Drops;


/**
 * Abstract punishment class
 *
 * @author Michael
 */
public abstract class Punishment extends Item{
    private final int healthAdjustment = -50;
    private final int pointAdjustment = -50;
    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }
}
