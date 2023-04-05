package integration;

import People.Bat;
import People.Enemy;
import People.Player;
import app.CollisionChecker;
import app.GamePanel;
import app.GameSettings;
import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;

public class CharacterCharacterCollisionTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    Player player = new Player(gamePanel, keyInputs);
    CollisionChecker cCheck = gamePanel.cCheck;

    @Test
    public void PlayerEnemyCollisionTest1(){
        player.setPlayerValues(5, 5, 0, "down");
        Bat bat = new Bat(gamePanel);
        bat.setWorldX(5 * GameSettings.getTileSize());
        bat.setWorldY(5 * GameSettings.getTileSize());
        bat.setSpeed(0);
        gamePanel.enemies = new Enemy[]{bat};

        System.out.println();

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(0, index);
    }

    @Test
    public void PlayerEnemyCollisionTest2(){
        player.setPlayerValues(5, 5, 0, "down");
        Bat bat = new Bat(gamePanel);
        bat.setWorldX(5 * GameSettings.getTileSize());
        bat.setWorldY(10 * GameSettings.getTileSize());
        bat.setSpeed(0);
        gamePanel.enemies = new Enemy[]{bat};

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(-1, index);
    }

    @Test
    public void PlayerEnemyCollisionTest3(){
        player.setPlayerValues(5, 5, 0, "down");
        Bat bat = new Bat(gamePanel);
        bat.setWorldX(10 * GameSettings.getTileSize());
        bat.setWorldY(5 * GameSettings.getTileSize());
        bat.setSpeed(0);
        gamePanel.enemies = new Enemy[]{bat};

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(-1, index);
    }

    @Test
    public void PlayerEnemyCollisionTest4(){
        player.setPlayerValues(5, 5, 0, "down");
        Bat bat = new Bat(gamePanel);
        bat.setWorldX(10 * GameSettings.getTileSize());
        bat.setWorldY(10 * GameSettings.getTileSize());
        bat.setSpeed(0);
        gamePanel.enemies = new Enemy[]{bat};

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(-1, index);
    }
}
