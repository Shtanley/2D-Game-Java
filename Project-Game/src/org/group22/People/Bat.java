package org.group22.People;

import org.group22.GameMap.Location;
import org.group22.app.GamePanel;

import java.util.Random;

public class Bat extends Entity {
    public Bat(GamePanel gp) {
        super(gp);

        name = "Green Slime";
        speed = 1;

        hitBox.x = 3;
        hitBox.y = 18;
        hitBox.width = 42;
        hitBox.height = 30;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/Enemy/greenslime_down_1");
        up2 = setup("/Enemy/greenslime_down_2");
        down1 = setup("/Enemy/greenslime_down_1");
        down2 = setup("/Enemy/greenslime_down_2");
        left1 = setup("/Enemy/greenslime_down_1");
        left2 = setup("/Enemy/greenslime_down_2");
        right1 = setup("/Enemy/greenslime_down_1");
        right1 = setup("/Enemy/greenslime_down_2");
    }

    public void setAction() {
        lockActionCount++;
        if(lockActionCount == 120) {
            Random rand = new Random();
            int n = rand.nextInt(100) + 1;

            if(n <= 25) {
                direction = "up";
            } else if( n > 25 && n <= 50) {
                direction = "down";
            } else if( n > 50 && n <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            lockActionCount = 0;
        }
    }
}
