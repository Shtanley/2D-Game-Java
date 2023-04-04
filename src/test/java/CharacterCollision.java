import Drops.*;
import People.Player;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class CharacterCollision {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    @Test
    public void testPlayerItemInteraction(){
        gamePanel.obj = new Item[4];

        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getPoints());

        Key key = new Key (0, 0 );
        gamePanel.obj[0] = key;

        player.playerInteraction(key);
        Assert.assertEquals(25, player.getPoints());

        Spikes spikes = new Spikes(0, 0 );
        gamePanel.obj[1] = spikes;

        player.playerInteraction(spikes);
        Assert.assertEquals(0, player.getPoints());
        Assert.assertEquals(player.getMaxHealth() - 50, player.getHealth());

        Potion potion = new Potion(0,0,0);
        gamePanel.obj[2] = potion;

        player.playerInteraction(potion);
        Assert.assertEquals(25 + 25, player.getPoints());
        Assert.assertEquals(player.getMaxHealth() - 50 + 50, player.getHealth());

        Door door = new Door(0,0);
        gamePanel.obj[3] = door;

        player.playerInteraction(door);
        Assert.assertEquals(50 + 50, player.getPoints());
        Assert.assertEquals(player.getMaxHealth() + 0, player.getHealth());
    }

    @Test
    public void testPlayerEnemyInteraction(){
        //Player player = new Player(gamePanel, keyInputs);



    }
}
