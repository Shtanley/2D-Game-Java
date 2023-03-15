package org.group22.Drops;

import org.group22.GameMap.Location;

import javax.imageio.ImageIO;
import java.io.IOException;

public abstract class Reward extends Item{
    private final int healthAdjustment = 10;
    private final int pointAdjustment = 10;
    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }

}
