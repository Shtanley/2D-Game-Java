package unit;

import app.GamePanel;
import app.KeyInputs;
import org.junit.Test;

import java.awt.event.KeyEvent;

public class KeyInputsTest {

    GamePanel gamePanel = new GamePanel();
    KeyInputs keyInputs = new KeyInputs(gamePanel);
    @Test
    public void test_TitleState_GameState(){
        int keyPressed = KeyEvent.VK_UP;
    }
}
