package unit;

import Drops.ItemFactory;
import GameMap.ComponentFactory;
import People.EnemyFactory;
import app.CollisionChecker;
import app.GamePanel;
import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;

public class ComponentFactoryTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = gamePanel.getKeyInputs();
    ComponentFactory cFactory = gamePanel.cFactory;
    ItemFactory iFactory = gamePanel.getiFactory();
    CollisionChecker cCheck = gamePanel.cCheck;
    EnemyFactory eFactory = gamePanel.geteFactory();


    @Test
    public void generateEmptyTestMap() {
        cFactory.loadMap("/Map/world_test_empty.txt");
        Assert.assertEquals(0 , cFactory.mapWidth);
        Assert.assertEquals(0 , cFactory.mapHeight);
    }

    @Test
    public void generateTestMap1() {
        cFactory.loadMap("/Map/world_test1.txt");
        Assert.assertEquals(9 , cFactory.mapWidth);
        Assert.assertEquals(10 , cFactory.mapHeight);
    }

    @Test
    public void generateTestMap2() {
        cFactory.loadMap("/Map/world_test2.txt");
        Assert.assertEquals(18 , cFactory.mapWidth);
        Assert.assertEquals(18 , cFactory.mapHeight);
    }

    @Test
    public void generateTestMapBig() {
        cFactory.loadMap("/Map/world_test_big.txt");
        Assert.assertEquals(50 , cFactory.mapWidth);
        Assert.assertEquals(50 , cFactory.mapHeight);
    }


//        iFactory.createItems("/Map/items_test.txt");
//        eFactory.createEnemies("/Map/enemies_test.txt");
}
