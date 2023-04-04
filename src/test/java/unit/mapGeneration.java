package unit;

import app.GamePanel;
import app.KeyInputs;
import org.junit.Assert;
import org.junit.Test;

public class mapGeneration {
    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);

    @Test
    public void generateTestMap() {
        System.out.println("Running generateTestMap");

        gamePanel.setupGame();
        gamePanel.setupLevel("test");

        System.out.println("Done!");
    }
}
