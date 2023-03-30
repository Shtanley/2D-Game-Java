package org.group22.Drops;

/**
 * Create spike punishment item
 *
 * @author Michael
 */
public class Spikes extends Punishment {
    public Spikes(int x, int y) {
        name = "Spikes";
        image = Item.setupSprite("/Object/peaks_1");
        worldX = x;
        worldY = y;
    }
}
