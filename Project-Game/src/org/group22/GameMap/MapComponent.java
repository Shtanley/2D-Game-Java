package org.group22.GameMap;

import org.group22.Drops.Item;
import org.group22.People.Entity;
import org.group22.app.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * MapComponent class
 * Manage component image and component movement
 *
 * abstract class for different types of map compoenents
 * @author Sameer
 * @author Michael
 * @author Dina
 */
public class MapComponent {
//    protected Location loc;
    protected Item item;
    public BufferedImage image;
    public boolean collision = false;

    // Getters
    public Item getItem() {
        return item;
    }


    public void setItem(Item item) {
        this.item = item;
    }

}
