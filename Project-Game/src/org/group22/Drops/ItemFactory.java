package org.group22.Drops;

import org.group22.app.GamePanel;

/**
 * ItemFactory class
 * Create items
 * @author Sameer
 */
public class ItemFactory {
    GamePanel gp;

    public ItemFactory(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Create items
     * Set item spawn location
     */
    public void createItem() {
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 33 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = 34 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new Key();
        gp.obj[2].worldX = 35 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new Key();
        gp.obj[3].worldX = 36 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4] = new Key();
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 10 * gp.tileSize;

        gp.obj[5] = new Key();
        gp.obj[5].worldX = 38 * gp.tileSize;
        gp.obj[5].worldY = 10 * gp.tileSize;

        gp.obj[6] = new Key();
        gp.obj[6].worldX = 39 * gp.tileSize;
        gp.obj[6].worldY = 10 * gp.tileSize;

        gp.obj[7] = new Potion();
        gp.obj[7].worldX = 11 * gp.tileSize;
        gp.obj[7].worldY = 10 * gp.tileSize;

        gp.obj[8] = new Door();
        gp.obj[8].worldX = 48 * gp.tileSize;
        gp.obj[8].worldY = 10 * gp.tileSize;
    }
}
