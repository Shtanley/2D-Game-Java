package integration;

import Drops.*;
import People.Player;
import app.GamePanel;

import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;


public class PlayerItemCollisionTest {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    Player player = new Player(gamePanel, keyInputs);


    @Test
    public void PlayerKeyCollisionTest(){
        Key key = new Key (0, 0 );
        gamePanel.obj = new Item[]{key};

        player.playerInteraction(key);

        Assert.assertEquals(25, player.getPoints());
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(1, player.getKeyCount());
    }

    @Test
    public void PlayerSpikesCollisionTest(){
        Spikes spikes = new Spikes(0, 0 );
        gamePanel.obj = new Item[]{spikes};

        player.playerInteraction(spikes);

        Assert.assertEquals(spikes.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth() + spikes.getHealthAdjustment(), player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

    @Test
    public void PlayerPotionCollisionTest1(){
        Potion potion = new Potion(0,0,0);
        gamePanel.obj = new Item[]{potion};

        player.playerInteraction(potion);

        Assert.assertEquals(potion.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

    @Test
    public void PlayerPotionCollisionTest2(){
        Potion potion = new Potion(0,0,0);
        gamePanel.obj = new Item[]{potion};

        player.adjustHealth(-25);
        player.playerInteraction(potion);

        Assert.assertEquals(potion.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

    @Test
    public void PlayerPotionCollisionTest3(){
        Potion potion = new Potion(0,0,0);
        gamePanel.obj = new Item[]{potion};

        player.adjustHealth(-50);
        player.playerInteraction(potion);

        Assert.assertEquals(potion.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

    @Test
    public void PlayerPotionCollisionTest4(){
        Potion potion = new Potion(0,0,0);
        gamePanel.obj = new Item[]{potion};

        player.adjustHealth(-100);
        player.playerInteraction(potion);

        Assert.assertEquals(potion.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth() - 50, player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

    @Test
    public void PlayerDoorCollisionTest(){
        Door door = new Door(0,0);
        gamePanel.obj = new Item[]{door};

        player.playerInteraction(door);

        Assert.assertEquals(door.getPointAdjustment(), player.getPoints());
        Assert.assertEquals(player.getMaxHealth(), player.getHealth());
        Assert.assertEquals(0, player.getKeyCount());
    }

}
