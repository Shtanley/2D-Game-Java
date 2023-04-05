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
    public void testEnemyCreation(){
        Skeleton skeleton = new Skeleton(gamePanel);
        Bat bat = new Bat(gamePanel);
        Slime slime = new Slime(gamePanel);

        eFactory.createEnemies("/Map/enemies01.txt");

        Assert.assertEquals(gamePanel.enemies[0].getClass(), bat.getClass());
        Assert.assertEquals(gamePanel.enemies[2].getClass(), skeleton.getClass());
        Assert.assertEquals(gamePanel.enemies[8].getClass(), slime.getClass());
    }
}
