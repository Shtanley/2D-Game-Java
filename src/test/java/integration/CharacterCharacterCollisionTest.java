package integration;

import People.*;
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
        Assert.assertFalse(player.isCollisionOff());
    }

    @Test
    public void PlayerEnemyCollisionTest2(){
        player.setPlayerValues(5, 5, 0, "down");
        Skeleton skeleton = new Skeleton(gamePanel);
        skeleton.setWorldX(5 * GameSettings.getTileSize());
        skeleton.setWorldY(10 * GameSettings.getTileSize());
        skeleton.setSpeed(0);
        gamePanel.enemies = new Enemy[]{skeleton};

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(-1, index);
        Assert.assertTrue(player.isCollisionOff());
    }

    @Test
    public void PlayerEnemyCollisionTest3(){
        player.setPlayerValues(5, 5, 0, "down");
        Slime slime = new Slime(gamePanel);
        slime.setWorldX(10 * GameSettings.getTileSize());
        slime.setWorldY(5 * GameSettings.getTileSize());
        slime.setSpeed(0);
        gamePanel.enemies = new Enemy[]{slime};

        int index = cCheck.checkEntity(player, gamePanel.enemies);
        Assert.assertEquals(-1, index);
        Assert.assertTrue(player.isCollisionOff());
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
        Assert.assertTrue(player.isCollisionOff());
    }

    @Test
    public void EnemyEnemyCollisionTest1(){
        Skeleton skeleton = new Skeleton(gamePanel);
        skeleton.setWorldX(10 * GameSettings.getTileSize());
        skeleton.setWorldY(10 * GameSettings.getTileSize());
        skeleton.setSpeed(0);
        Slime slime = new Slime(gamePanel);
        slime.setWorldX(10 * GameSettings.getTileSize());
        slime.setWorldY(10 * GameSettings.getTileSize());
        slime.setSpeed(0);
        gamePanel.enemies = new Enemy[]{skeleton, slime};

        int index = cCheck.checkEntity(skeleton, gamePanel.enemies);
        Assert.assertEquals(1, index);
        Assert.assertFalse(skeleton.isCollisionOff());

        index = cCheck.checkEntity(slime, gamePanel.enemies);
        Assert.assertEquals(0, index);
        Assert.assertFalse(slime.isCollisionOff());
    }
    @Test
    public void EnemyEnemyCollisionTest2(){
        Bat bat1 = new Bat(gamePanel);
        bat1.setWorldX(5 * GameSettings.getTileSize());
        bat1.setWorldY(5 * GameSettings.getTileSize());
        bat1.setSpeed(0);
        Bat bat2 = new Bat(gamePanel);
        bat2.setWorldX(10 * GameSettings.getTileSize());
        bat2.setWorldY(10 * GameSettings.getTileSize());
        bat2.setSpeed(0);
        gamePanel.enemies = new Enemy[]{bat1, bat2};

        int index = cCheck.checkEntity(bat1, gamePanel.enemies);
        Assert.assertEquals(-1, index);
        Assert.assertTrue(bat1.isCollisionOff());

        index = cCheck.checkEntity(bat2, gamePanel.enemies);
        Assert.assertEquals(-1, index);
        Assert.assertTrue(bat2.isCollisionOff());
    }
}
