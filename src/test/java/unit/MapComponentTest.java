package unit;

import Drops.ItemFactory;
import Drops.Key;
import GameMap.ComponentFactory;
import GameMap.MapComponent;
import app.GamePanel;
import org.junit.Assert;
import org.junit.Test;

public class MapComponentTest {
    GamePanel gamePanel;
    ItemFactory iFactory = new ItemFactory(gamePanel);
    ComponentFactory cFactory = new ComponentFactory(gamePanel);
    public MapComponent testCases;


    @Test
    public void creation(){
        gamePanel = new GamePanel();
        testCases = new MapComponent();
        Assert.assertNotNull(testCases);

    }
    @Test
    public void collisionCheck(){
        gamePanel = new GamePanel();
        testCases = new MapComponent();
        Assert.assertFalse(testCases.isCollisionOn());
        testCases.setCollisionOn();
        Assert.assertTrue(testCases.isCollisionOn());

    }
    @Test
    public void getItem(){
        gamePanel = new GamePanel();
        testCases = new MapComponent();
        Key key = new Key(0, 0);
        testCases.setItem(key);
        Assert.assertEquals(key, testCases.getItem());

    }

    @Test
    public void getImage(){
        gamePanel = new GamePanel();
        testCases = new MapComponent();
        testCases.setImage(cFactory.setup("/images/ground01"));
        Assert.assertNotNull(testCases.getImage());

    }

}
