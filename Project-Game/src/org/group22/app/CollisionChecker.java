package org.group22.app;

import org.group22.Drops.Item;
import org.group22.GameMap.ComponentFactory;
import org.group22.People.Entity;

import java.util.ArrayList;

/**
 * CollisionChecker class
 * Check collision between player and map
 * @author Sameer
 */
public class CollisionChecker {
    GamePanel gp;
    ArrayList<Item> obj;
    ComponentFactory cFactory;
    int tileSize;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
        this.obj = gp.obj;
        this.cFactory = gp.cFactory;
        this.tileSize = gp.tileSize;
    }

    /**
     * Check collision between player and map
     * Checks all tiles on the screen
     * @param entity entity for which we are checking collision with components
     */
    public void checkComponent(Entity entity) {
        int entityLeftX = entity.worldX + entity.hitBox.x;
        int entityRightX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopY = entity.worldY + entity.hitBox.y;
        int entityBottomY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftX / tileSize;
        int entityRightCol = entityRightX / tileSize;
        int entityTopRow = entityTopY / tileSize;
        int entityBottomRow = entityBottomY / tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / tileSize;
                tileNum1 = cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = cFactory.mapTileNum[entityRightCol][entityTopRow];
                if (cFactory.mc[tileNum1].collision || cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / tileSize;
                tileNum1 = cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (cFactory.mc[tileNum1].collision || cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / tileSize;
                tileNum1 = cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                if (cFactory.mc[tileNum1].collision || cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / tileSize;
                tileNum1 = cFactory.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if (cFactory.mc[tileNum1].collision || cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    /**
     * Check collision between player and item
     * Checks only the limited number of items
     * @param entity    the entity for which we check collision with item
     * @param isPlayer  boolean for whether the entity is the player or not
     * @return index of item
     */
    public int checkItem(Entity entity, boolean isPlayer) {
        int index = 999;

        for(int i = 0; i < obj.size(); i++) {
            if(obj.get(i) != null) {
                // Get entity hitbox coordinates
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Get item hitbox coordinates
                obj.get(i).hitBox.x += obj.get(i).worldX;
                obj.get(i).hitBox.y += obj.get(i).worldY;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(obj.get(i).hitBox)) {
                            if (obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(obj.get(i).hitBox)) {
                            if (obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(obj.get(i).hitBox)) {
                            if (obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(obj.get(i).hitBox)) {
                            if (obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                }
                // Reset hitbox coordinates
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                obj.get(i).hitBox.x = obj.get(i).hitBoxDefaultX;
                obj.get(i).hitBox.y = obj.get(i).hitBoxDefaultY;
            }

        }
        return index;
    }
}
