import Drops.Item;
import Drops.Key;
import Drops.Potion;
import Drops.Spikes;
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
        gamePanel.obj = new Item[3];

        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getHealth(), player.getMaxHealth());
        Assert.assertEquals(player.getPoints(), 0);

        Key key = new Key (0, 0 );
        gamePanel.obj[0] = key;

        player.playerInteraction(key);
        Assert.assertEquals(player.getPoints(), 25);

        Spikes spikes = new Spikes(0, 0 );
        gamePanel.obj[1] = spikes;

        player.playerInteraction(spikes);
        Assert.assertEquals(player.getPoints(), 0);
        Assert.assertEquals(player.getHealth(), player.getMaxHealth() - 50);

        Potion potion = new Potion(0,0,0);
        gamePanel.obj[2] = potion;

        player.playerInteraction(potion);
        Assert.assertEquals(player.getPoints(), 25 + 25);
    }

    @Test
    public void testPlayerEnemyInteraction(){
        Player player = new Player(gamePanel, keyInputs);



    }
}
