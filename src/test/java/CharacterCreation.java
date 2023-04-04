import People.Player;
import app.GamePanel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CharacterCreation {
    private final GamePanel testGP = new GamePanel();
    private final Player testPlayer = new Player(testGP, testGP.getKeyInputs());

    @Test
    public void testPlayerCreation(){
        System.out.println("Testing!");
    }
}
