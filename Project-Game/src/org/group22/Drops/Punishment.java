package org.group22.Drops;

/**
 * Abstract punishment class
 *
 * @author Michael
 * @author Dina
 *
 */
public abstract class Punishment extends Item{
    public int getHealthAdjustment() { return -50;}
    public int getPointAdjustment() {
        return -25;
    }
}
