package org.group22.Drops;

import java.awt.image.BufferedImage;

/**
 * Create spike punishment item
 *
 * @author Michael
 */
public class Spikes extends Punishment {
    public Spikes(int x, int y) {
        setName("Spikes");
        setImage(Item.setupSprite("/Object/peaks_1"));
        setWorldX(x);
        setWorldY(y);
    }

    /**
     * Static method for getting spikes sprite
     *
     * @return a BufferedImage spikes sprite
     */
    public static BufferedImage getSprite(){
        return Item.setupSprite("/Object/peaks_1");
    }
}
