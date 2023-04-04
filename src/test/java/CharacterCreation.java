import People.Player;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class CharacterCreation {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    @Test
    public void testPlayerCreation(){
        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getHealth(), player.getMaxHealth());
        Assert.assertEquals(player.getPoints(), 0);
    }

    @Test
    public void testPlayerInteraction(){
        Player player = new Player(gamePanel, keyInputs);



    }
}
