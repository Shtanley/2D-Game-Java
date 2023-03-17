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
        int entityLeftX = entity.worldX + entity.hitBox.x;
        int entityRightX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopY = entity.worldY + entity.hitBox.y;
        int entityBottomY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                if (gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
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
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                obj.hitBox.x += obj.worldX;
                obj.hitBox.y += obj.worldY;
                switch (entity.direction) {
                    case "up" -> entity.hitBox.y -= entity.speed;
                    case "down" -> entity.hitBox.y += entity.speed;
                    case "left" -> entity.hitBox.x -= entity.speed;
                    case "right" -> entity.hitBox.x += entity.speed;
                }
                if(entity.hitBox.intersects(obj.hitBox)) {
                    // Enemies are supposed to be able to walk through items unaffected
//                    if(obj.collision) {
//                        entity.collisionOn = true;
//                    }
                    if(isPlayer) {
                        result = obj;
                    }
                    break;
                }
                // Reset hitbox coordinates
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                obj.hitBox.x = obj.hitBoxDefaultX;
                obj.hitBox.y = obj.hitBoxDefaultY;
            }
        }

        // Bonus rewards ArrayList
        for(BonusReward bonus : gp.tempItems) {
            // Get entity hitbox coordinates
            entity.hitBox.x += entity.worldX;
            entity.hitBox.y += entity.worldY;
            // Get item hitbox coordinates
            bonus.hitBox.x += bonus.worldX;
            bonus.hitBox.y += bonus.worldY;
            switch (entity.direction) {
                case "up" -> entity.hitBox.y -= entity.speed;
                case "down" -> entity.hitBox.y += entity.speed;
                case "left" -> entity.hitBox.x -= entity.speed;
                case "right" -> entity.hitBox.x += entity.speed;
            }
            if(entity.hitBox.intersects(bonus.hitBox)) {
                // Enemies are supposed to be able to walk through items unaffected
//                    if(obj.collision) {
//                        entity.collisionOn = true;
//                    }
                if(isPlayer) {
                    result = bonus;
                }
                break;
            }
            // Reset hitbox coordinates
            entity.hitBox.x = entity.hitBoxDefaultX;
            entity.hitBox.y = entity.hitBoxDefaultY;
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
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                target[i].hitBox.x += target[i].worldX;
                target[i].hitBox.y += target[i].worldY;

                switch (entity.direction) {
                    case "up" -> entity.hitBox.y -= entity.speed;
                    case "down" -> entity.hitBox.y += entity.speed;
                    case "left" -> entity.hitBox.x -= entity.speed;
                    case "right" -> entity.hitBox.x += entity.speed;
                }
                if(entity.hitBox.intersects(target[i].hitBox)) {
                    if(target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                // Reset hitbox coordinates
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target[i].hitBox.x = target[i].hitBoxDefaultX;
                target[i].hitBox.y = target[i].hitBoxDefaultY;
            }

        }
        return index;
    }
}
