package org.group22.People;

import org.group22.app.GamePanel;

public abstract class Enemy extends Entity {

    /**
     * Constructs new Enemy
     */
    public Enemy(GamePanel gp){
        super(gp);
    }


    public abstract void setAction();

    public void update() {
        setAction();

        collisionOn = false;
        gp.cCheck.checkComponent(this);

        // Collision detection
        collisionOn = false;
        gp.cCheck.checkComponent(this);
        gp.cCheck.checkEntity(this, gp.enemies);

        if(!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCount++;
        if(spriteCount > 10) {
            spriteCount = 0;
            if(spriteNum == 1)
                spriteNum = 2;
            else
                spriteNum = 1;
        }
    };


}
