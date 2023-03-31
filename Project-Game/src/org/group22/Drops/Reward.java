package org.group22.Drops;


/**
 * Abstract reward class
 *
 * @author Michael
 */
public abstract class Reward extends Item{
    public int getHealthAdjustment() { return 0;}
    public int getPointAdjustment() {
        return 25;
    }

}
