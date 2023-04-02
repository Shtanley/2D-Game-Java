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

        // Objects array
        for(Item obj : gp.obj) {
            if(obj != null) {
                // Get item hit box coordinates
                getItemHitboxCoord(obj);
                moveHitbox(entity);
                if(entity.getHitBox().intersects(obj.getHitBox())) {
                    if(isPlayer) {
                        result = obj;
                    }
                    break;
                }
                // Reset hit box coordinates
                resetEntityHitbox(entity);
                obj.getHitBox().x = obj.hitBoxDefaultX;
                obj.getHitBox().y = obj.hitBoxDefaultY;
            }
        }

        // Bonus rewards ArrayList
        for(BonusReward bonus : gp.tempItems) {
            // Get item hit box coordinates
            getItemHitboxCoord(bonus);
            moveHitbox(entity);
            if(entity.getHitBox().intersects(bonus.getHitBox())) {
                if(isPlayer) {
                    result = bonus;
                }
                break;
            }
            // Reset hit box coordinates
            resetEntityHitbox(entity);
            bonus.getHitBox().x = bonus.hitBoxDefaultX;
            bonus.getHitBox().y = bonus.hitBoxDefaultY;

        }

        return result;
    }

    /**
     * Helper function to get item hitbox coordinates
     *
     * @param obj primary object
     */
    private static void getItemHitboxCoord(Item obj) {
        obj.getHitBox().x += obj.getWorldX();
        obj.getHitBox().y += obj.getWorldY();
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
                // Get item hit box coordinates
                target[i].getHitBox().x += target[i].getWorldX();
                target[i].getHitBox().y += target[i].getWorldY();

                moveHitbox(entity);
                if(entity.getHitBox().intersects(target[i].getHitBox())) {
                    if(target[i] != entity) {
                        entity.turnOnCollision();
                        index = i;
                    }
                }
                // Reset hit box coordinates
                resetEntityHitbox(entity);
                resetEntityHitbox(target[i]);
            }
        }
        return index;
    }

    /**
     * Helper function to reset entity hitbox to default position
     *
     * @param entity primary entity
     */
    private static void resetEntityHitbox(Entity entity) {
        entity.getHitBox().x = entity.getHitBoxDefaultX();
        entity.getHitBox().y = entity.getHitBoxDefaultY();
    }

    /**
     * Helper function to move the entity hitbox as the entity itself moves
     *
     * @param entity primary entity
     */
    private void moveHitbox(Entity entity) {
        // Get entity hit box coordinates
        entity.getHitBox().x += entity.getWorldX();
        entity.getHitBox().y += entity.getWorldY();

        switch (entity.getDirection()) {
            case "up" -> entity.getHitBox().y -= entity.getSpeed();
            case "down" -> entity.getHitBox().y += entity.getSpeed();
            case "left" -> entity.getHitBox().x -= entity.getSpeed();
            case "right" -> entity.getHitBox().x += entity.getSpeed();
        }
    }
}