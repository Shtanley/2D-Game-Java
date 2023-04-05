package unit;

import Drops.*;
import People.*;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    ItemFactory iFactory = new ItemFactory(gamePanel);
    EnemyFactory eFactory = new EnemyFactory(gamePanel);

    @Test
    public void testPlayerCreation(){
        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getPoints());
    }
}
