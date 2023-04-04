import Drops.*;
import People.Player;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class CharacterCreation {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    ItemFactory iFactory = new ItemFactory(gamePanel);
    @Test
    public void testPlayerCreation(){
        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getPoints());
    }

    @Test
    public void testItemCreation() {
        gamePanel.player = new Player(gamePanel, keyInputs);

        Key key = new Key(0, 0);
        Spikes spikes = new Spikes(0, 0);
        Potion potion = new Potion(0, 0, 10);
        Door door = new Door(0, 0);

        iFactory.createItems("/Map/items01.txt");
        Assert.assertEquals(gamePanel.obj[0].getClass(), key.getClass());
        Assert.assertEquals(0, key.getHealthAdjustment());
        Assert.assertEquals(25, key.getPointAdjustment());

        Assert.assertEquals(gamePanel.obj[3].getClass(), spikes.getClass());
        Assert.assertEquals(-50, spikes.getHealthAdjustment());
        Assert.assertEquals(-25, spikes.getPointAdjustment());

        Assert.assertEquals(gamePanel.obj[7].getClass(), door.getClass());
        Assert.assertEquals(0,door.getHealthAdjustment());
        Assert.assertEquals(50,door.getPointAdjustment());


        gamePanel.tempItems.add(new Potion(0, 0, 10));
        Assert.assertEquals(gamePanel.tempItems.get(0).getClass(), potion.getClass());
        Assert.assertEquals(50,potion.getHealthAdjustment());
        Assert.assertEquals(50,potion.getHealthAdjustment());
        Assert.assertEquals(10,potion.getBirthTime());
    }

    @Test
    public void testEnemyCreation(){

    }
}
