package org.group22.Drops;

import org.group22.People.Bat;
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
        gp.obj[0].worldX = 12 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Potion();
        gp.obj[1].worldX = 11 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;

        gp.obj[2] = new Door();
        gp.obj[2].worldX = 48 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;
    }

    public void setEnemy() {
        gp.bat[0] = new Bat(gp);
        gp.bat[0].worldX = 10 * gp.tileSize;
        gp.bat[0].worldY = 10 * gp.tileSize;

        gp.bat[1] = new Bat(gp);
        gp.bat[1].worldX = 11 * gp.tileSize;
        gp.bat[1].worldY = 10 * gp.tileSize;
        
    }
}
