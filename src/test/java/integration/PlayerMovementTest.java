package integration;

import People.Player;
import app.GamePanel;
import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;

public class PlayerMovementTest {
    GamePanel testGP = new GamePanel();
    KeyInputs inputs = new KeyInputs(testGP);
    Player testPlayer = new Player(testGP, inputs);

    @Test
    public void testPlayerMoveUp() {
        testPlayer.resetPlayer();
        testPlayer.setPlayerValues(1, 1, 8, "up");
        int y = testPlayer.getWorldY(); // original position is 48
//        System.out.print("\tOld pos = "+y+"\n");
        inputs.setUptoTrue();
        testPlayer.callUpdatePostion(); // player should move up by 8, i.e. 48 - 8 = 40
//        System.out.print("\tNew pos = "+testPlayer.getWorldY()+"\n");

        Assert.assertTrue(testPlayer.getWorldY() < y);
    }

    @Test
    public void testPlayerMoveDown() {
        testPlayer.resetPlayer();
        testPlayer.setPlayerValues(1, 1, 8, "down");
        int y = testPlayer.getWorldY(); // original position is 48
        inputs.setDowntoTrue();
        testPlayer.callUpdatePostion(); // player should move up by 8, i.e. 48 + 8 = 56

        Assert.assertTrue(testPlayer.getWorldY() > y);
    }

    @Test
    public void testPlayerMoveLeft() {
        testPlayer.resetPlayer();
        testPlayer.setPlayerValues(1, 1, 8, "left");
        int x = testPlayer.getWorldX(); // original position is 48
        inputs.setLefttoTrue();
        testPlayer.callUpdatePostion(); // player should move up by 8, i.e. 48 - 8 = 40

        Assert.assertTrue(testPlayer.getWorldX() < x);
    }

    @Test
    public void testPlayerMoveRight() {
        testPlayer.resetPlayer();
        testPlayer.setPlayerValues(1, 1, 8, "right");
        int x = testPlayer.getWorldX(); // original position is 48
        inputs.setRighttoTrue();
        testPlayer.callUpdatePostion(); // player should move up by 8, i.e. 48 + 8 = 56

        Assert.assertTrue(testPlayer.getWorldX() > x);
    }
}
