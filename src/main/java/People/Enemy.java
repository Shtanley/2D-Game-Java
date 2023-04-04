package People;

import app.GamePanel;

/**
 * Enemy class
 * Manage Enemy image and Enemy movement
 * @author Dina
 * @author Michael
 * @author Sameer
 */
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

        turnOffCollision();
        gp.cCheck.checkComponent(this);

        // Collision detection
        turnOffCollision();
        gp.cCheck.checkComponent(this);
        gp.cCheck.checkEntity(this, gp.enemies);

        if(isCollisionOff()) {
            switch (getDirection()) {
                case "up" -> setWorldY(getWorldY() - getSpeed());
                case "down" -> setWorldY(getWorldY() + getSpeed());
                case "left" -> setWorldX(getWorldX() - getSpeed());
                case "right" -> setWorldX(getWorldX() + getSpeed());
            }
        }

        setSpriteCount(getSpriteCount() + 1);
        if(getSpriteCount() > 10) {
            setSpriteCount(0);
            if(getSpriteNum() == 1)
                setSpriteNum(2);
            else
                setSpriteNum(1);
        }
    }


}
