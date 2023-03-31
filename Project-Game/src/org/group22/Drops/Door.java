package org.group22.Drops;

/**
 * Door class
 * Create door item
 *
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Door extends Item {

    public Door(int x, int y) {
        name = "Door";
        image = Item.setupSprite("/Object/newdoor");
        worldX = x;
        worldY = y;
        collision = true;
    }

    public int getHealthAdjustment() {
        return 0;
    }
    public int getPointAdjustment() {
        return 50;
    }

}
