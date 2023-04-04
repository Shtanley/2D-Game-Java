package main.java.GameMap;

import main.java.Drops.Item;

import java.awt.image.BufferedImage;

/**
 * MapComponent class
 * Manage component image and component movement
 * abstract class for different types of map components
 * @author Sameer
 * @author Michael
 * @author Dina
 */
public class MapComponent {
    protected Item item;
    private BufferedImage image;
    private boolean collision = false;

    // Getters
    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollisionOn() {
        return collision;
    }

    public void setCollisionOn() {
        collision = true;
    }

}
