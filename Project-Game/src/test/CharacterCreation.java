package test;

import main.java.People.Player;
import main.java.app.GamePanel;
import org.testng.annotations.Test;

public class CharacterCreation {
    private final GamePanel testGP = new GamePanel();
    private final Player testPlayer = new Player(testGP, testGP.getKeyInputs());

    @Test
    public void testPlayerCreation(){

    }
}
