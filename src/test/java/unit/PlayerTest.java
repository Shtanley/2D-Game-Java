package unit;

import People.*;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);

    @Test
    public void testPlayerCreation(){
        Player player = new Player(gamePanel, keyInputs);
        Assert.assertNotNull(player);
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getPoints());
    }

    Player testPlayer = new Player(gamePanel, keyInputs);
    @Test
    public void testPlayerDeath() {
        testPlayer.setHealth(0);

        Assert.assertEquals(true, testPlayer.isDead());
    }

    @Test
    public void testPlayerNegativePoints() {
        testPlayer.setPoints(-69);

        Assert.assertEquals(true, testPlayer.isDead());
    }

    @Test
    public void testHPUpdateWhenMaxHP() {
        testPlayer.adjustHealth(20);

        Assert.assertEquals(200, testPlayer.getHealth());
    }

    @Test
    public void testHPUpdateWhenNotMaxHP() {
        testPlayer.adjustHealth(-20);

        Assert.assertEquals(180, testPlayer.getHealth());
    }

    @Test
    public void testUpdateSprites() {
        for(int i = 0; i < 11; i++) {   // change sprite every 10 ticks
            testPlayer.setSpriteCount(i);
            testPlayer.callUpdateSprites();
        }

        Assert.assertEquals(2, testPlayer.getSpriteNum());
    }

    @Test
    public void testPlayerDirectionUp() {
        keyInputs.setUptoTrue();
        testPlayer.callUpdateDirection();

        Assert.assertEquals("up", testPlayer.getDirection());
    }

    @Test
    public void testPlayerDirectionDown() {
        keyInputs.setDowntoTrue();
        testPlayer.callUpdateDirection();

        Assert.assertEquals("down", testPlayer.getDirection());
    }

    @Test
    public void testPlayerDirectionLeft() {
        keyInputs.setLefttoTrue();
        testPlayer.callUpdateDirection();

        Assert.assertEquals("left", testPlayer.getDirection());
    }

    @Test
    public void testPlayerDirectionRight() {
        keyInputs.setRighttoTrue();
        testPlayer.callUpdateDirection();

        Assert.assertEquals("right", testPlayer.getDirection());
    }
}
