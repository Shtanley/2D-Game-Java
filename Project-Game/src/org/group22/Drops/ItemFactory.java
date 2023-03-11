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

    public void createItem() {
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Potion();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
    }
}
