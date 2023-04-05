package unit;

import Drops.*;
import People.*;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class ItemFactoryTest {
    GamePanel gamePanel = new GamePanel();
    ItemFactory iFactory = new ItemFactory(gamePanel);

    @Test
    public void CreateItemsTest() {
        iFactory.createItems("/Map/items_test.txt");

        Item key = gamePanel.obj[0];
        Assert.assertEquals(key.getClass(), Key.class);
        Assert.assertEquals(0, key.getHealthAdjustment());
        Assert.assertEquals(25, key.getPointAdjustment());

        Item spikes = gamePanel.obj[1];
        Assert.assertEquals(spikes.getClass(), Spikes.class);
        Assert.assertEquals(-50, spikes.getHealthAdjustment());
        Assert.assertEquals(-25, spikes.getPointAdjustment());

        Item door = gamePanel.obj[2];
        Assert.assertEquals(door.getClass(), Door.class);
        Assert.assertEquals(0,door.getHealthAdjustment());
        Assert.assertEquals(50,door.getPointAdjustment());

        Assert.assertEquals(1, iFactory.getNumKeys());
    }

    @Test
    public void CreateItemsEmptyTest() {
        iFactory.createItems("/Map/items_empty.txt");
        for(Item item : gamePanel.obj) {
            Assert.assertNull(item);
        }

        Assert.assertEquals(0, iFactory.getNumKeys());
    }


}
