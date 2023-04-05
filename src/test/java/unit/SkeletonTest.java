
package unit;

import Drops.*;
import GameMap.MapComponent;
import People.*;
import app.GamePanel;
import app.KeyInputs;

import org.junit.Assert;
import org.junit.Test;

public class SkeletonTest {

    GamePanel gamePanel = new GamePanel();
    EnemyFactory eFactory = new EnemyFactory(gamePanel);
    public Skeleton skeleton;
    public Entity entity;

    @Test
    public void creation(){
        skeleton = new Skeleton(gamePanel);
        Assert.assertNotNull(skeleton);
    }

    @Test
    public void Path(){
        skeleton = new Skeleton(gamePanel);
        String dirR= "R";
        String dirL= "L";
        skeleton.addToPath(dirR);
        Assert.assertNotNull(skeleton);
        Assert.assertEquals(false, skeleton.verifyPath());
        skeleton.addToPath(dirL);
        Assert.assertEquals(true, skeleton.verifyPath());
    }

    @Test
    public void Image(){
        skeleton = new Skeleton(gamePanel);
        entity = skeleton;
        skeleton.getImage();
        Assert.assertNotNull(entity.getUp1());

    }

}
