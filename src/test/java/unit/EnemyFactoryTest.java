package unit;

import Drops.*;
import People.*;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class EnemyFactoryTest {
    GamePanel gamePanel = new GamePanel();
    EnemyFactory eFactory = new EnemyFactory(gamePanel);

    @Test
    public void CreateEnemiesTest(){
        eFactory.createEnemies("/Map/enemies_test.txt");

        Enemy bat = gamePanel.enemies[0];
        Assert.assertEquals(bat.getClass(), Bat.class);
        Enemy skeleton = gamePanel.enemies[1];
        Assert.assertEquals(skeleton.getClass(), Skeleton.class);
        Enemy slime = gamePanel.enemies[2];
        Assert.assertEquals(slime.getClass(), Slime.class);
    }

    @Test
    public void CreateEnemiesEmptyTest() {
        eFactory.createEnemies("/Map/enemies_empty.txt");
        for(Enemy enemy : gamePanel.enemies) {
            Assert.assertNull(enemy);
        }
    }
}
