package integration;

import People.Player;
import app.GamePanel;
import app.KeyInputs;
import org.junit.Test;

public class CharacterCharacterCollisionTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    Player player = new Player(gamePanel, keyInputs);

    @Test
    public void testPlayerEnemyInteraction(){
        //Player player = new Player(gamePanel, keyInputs);

    }
}
