package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Punishment extends Item{
    private final int healthAdjustment = -50;
    private final int pointAdjustment = -50;
    public Punishment() {
        name = "Punishment";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Object/tent.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getHealthAdjustment() { return healthAdjustment;}
    public int getPointAdjustment() {
        return pointAdjustment;
    }
}
