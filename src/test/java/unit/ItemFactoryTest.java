package unit;

import Drops.*;
import GameMap.ComponentFactory;
import People.*;
import app.GamePanel;

import app.GameSettings;
import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


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

    @Test
    public void SpawnPotionTest(){
        Player player = gamePanel.player;
        player.setPlayerValues(25, 25, 0, "down");
        ComponentFactory cFactory = gamePanel.cFactory;
        gamePanel.obj = new Item[]{};
        cFactory.loadMap("/Map/world_test_big.txt");

        for(int i = 0; i < 100; i++) {
            Potion newPotion = iFactory.spawnPotion();
            int x = newPotion.getWorldX() / GameSettings.getTileSize();
            int y = newPotion.getWorldY() / GameSettings.getTileSize();

            Assert.assertTrue(x >= 15);
            Assert.assertTrue(x <= 35);
            Assert.assertTrue(y >= 15);
            Assert.assertTrue(y <= 35);
            Assert.assertFalse(x == 25 && y == 25);
        }
    }


}
