package org.group22.app;

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
     * Check collision between player and map
     * Checks all tiles on the screen
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
     * Checks only the limited number of items
     *
     * @param entity entity for which collision is being checked
     * @param isPlayer boolean specifying whether entity is the player or not
     * @return index of item
     */
    public int checkItem(Entity entity, boolean isPlayer) {
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                gp.obj[i].hitBox.x += gp.obj[i].worldX;
                gp.obj[i].hitBox.y += gp.obj[i].worldY;

                switch(entity.direction) {
                case "up":
                    entity.hitBox.y -= entity.speed;
                    break;
                case "down":
                    entity.hitBox.y += entity.speed;
                    break;
                case "left":
                    entity.hitBox.x -= entity.speed;
                    break;
                case "right":
                    entity.hitBox.x += entity.speed;
                    break;
                }
                if(entity.hitBox.intersects(gp.obj[i].hitBox)) {
                    if(gp.obj[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if(isPlayer) {
                        index = i;
                    }
                }
                // Reset hitbox coordinates
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gp.obj[i].hitBox.x = gp.obj[i].hitBoxDefaultX;
                gp.obj[i].hitBox.y = gp.obj[i].hitBoxDefaultY;
            }

        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                target[i].hitBox.x += target[i].worldX;
                target[i].hitBox.y += target[i].worldY;

                switch(entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        break;
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
