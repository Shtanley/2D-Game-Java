package org.group22.app;

import org.group22.Drops.BonusReward;
import org.group22.Drops.Item;
import org.group22.People.Entity;

/**
 * CollisionChecker class
 * Check collision between player and map
 *
 * @author Sameer
 */
public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Check collision between entity and map
     * Checks for each map component
     *
     * @param entity entity for which collision is being checked
     */

    public void checkComponent(Entity entity) {
        int entityLeftX = entity.getWorldX() + entity.getHitBox().x;
        int entityRightX = entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width;
        int entityTopY = entity.getWorldY() + entity.getHitBox().y;
        int entityBottomY = entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                if (gp.cFactory.mc[tileNum1].isCollisionOn() || gp.cFactory.mc[tileNum2].isCollisionOn()) {
                    entity.turnOnCollision();
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].isCollisionOn() || gp.cFactory.mc[tileNum2].isCollisionOn()) {
                    entity.turnOnCollision();
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].isCollisionOn() || gp.cFactory.mc[tileNum2].isCollisionOn()) {
                    entity.turnOnCollision();
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.getSpeed()) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].isCollisionOn() || gp.cFactory.mc[tileNum2].isCollisionOn()) {
                    entity.turnOnCollision();
                }
            }
        }
    }

    /**
     * Check collision between player and item
     * Checks for all items in the game
     *
     * @param entity entity for which collision is being checked
     * @param isPlayer boolean specifying whether entity is the player or not
     * @return index of item
     */

    public Item checkItem(Entity entity, boolean isPlayer) {
        Item result = null;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                gp.obj[i].hitBox.x += gp.obj[i].worldX;
                gp.obj[i].hitBox.y += gp.obj[i].worldY;
                moveHitbox(entity);
                if(entity.hitBox.intersects(gp.obj[i].hitBox)) {
                    if(gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if(isPlayer) {
                        result = obj;
                    }
                    break;
                }
                // Reset hit box coordinates
                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();
                obj.hitBox.x = obj.hitBoxDefaultX;
                obj.hitBox.y = obj.hitBoxDefaultY;
            }
        }

        // Bonus rewards ArrayList
        for(BonusReward bonus : gp.tempItems) {
            // Get entity hit box coordinates
            entity.getHitBox().x += entity.getWorldX();
            entity.getHitBox().y += entity.getWorldY();
            // Get item hit box coordinates
            bonus.hitBox.x += bonus.worldX;
            bonus.hitBox.y += bonus.worldY;
            switch (entity.getDirection()) {
                case "up" -> entity.getHitBox().y -= entity.getSpeed();
                case "down" -> entity.getHitBox().y += entity.getSpeed();
                case "left" -> entity.getHitBox().x -= entity.getSpeed();
                case "right" -> entity.getHitBox().x += entity.getSpeed();
            }
            if(entity.getHitBox().intersects(bonus.hitBox)) {
                if(isPlayer) {
                    result = bonus;
                }
                break;
            }
            // Reset hit box coordinates
            entity.getHitBox().x = entity.getHitBoxDefaultX();
            entity.getHitBox().y = entity.getHitBoxDefaultY();
            bonus.hitBox.x = bonus.hitBoxDefaultX;
            bonus.hitBox.y = bonus.hitBoxDefaultY;

        }

        return result;
    }

    /**
     * Check collision between entity and every element of target
     * Primarily intended for checking collisions between the player and the enemies in the game
     *
     * @param entity primary entity
     * @param target array of entities
     * @return the index of the entity in target it collides with
     */
    public int checkEntity(Entity entity, Entity[] target) {
        int index = -1;

        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                // Get entity hit box coordinates
                entity.getHitBox().x += entity.getWorldX();
                entity.getHitBox().y += entity.getWorldY();
                // Get item hit box coordinates
                target[i].getHitBox().x += target[i].getWorldX();
                target[i].getHitBox().y += target[i].getWorldY();

                moveHitbox(entity);
                if(entity.hitBox.intersects(target[i].hitBox)) {
                    if(target[i] != entity) {
                        entity.turnOnCollision();
                        index = i;
                    }
                }
                // Reset hit box coordinates
                entity.getHitBox().x = entity.getHitBoxDefaultX();
                entity.getHitBox().y = entity.getHitBoxDefaultY();
                target[i].getHitBox().x = target[i].getHitBoxDefaultX();
                target[i].getHitBox().y = target[i].getHitBoxDefaultY();
            }

        }
        return index;
    }

    /**
     * Helper function to move the entity hitbox as the entity itself moves
     *
     * @param entity primary entity
     */
    private void moveHitbox(Entity entity) {
        switch (entity.direction) {
            case "up" -> entity.hitBox.y -= entity.speed;
            case "down" -> entity.hitBox.y += entity.speed;
            case "left" -> entity.hitBox.x -= entity.speed;
            case "right" -> entity.hitBox.x += entity.speed;
        }
    }
}
