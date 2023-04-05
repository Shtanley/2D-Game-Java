package unit;

import UI.UI;
import app.GamePanel;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class UITests {

    GamePanel gamePanel = new GamePanel();
    UI ui = new UI(gamePanel);
    Graphics2D g2d;

    @Test
    public void testUI(){
        Assert.assertNotNull(ui);
    }

    @Test
    public void testUIGettersSetters(){
        Assert.assertEquals(0, ui.getTitleCmdNum());
        Assert.assertEquals(0, ui.getDiffCmdNum());

        ui.setDiffCmdNum(5);
        ui.setTitleCmdNum(5);

        Assert.assertEquals(5, ui.getTitleCmdNum());
        Assert.assertEquals(5, ui.getDiffCmdNum());
    }
}
