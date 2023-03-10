package org.group22.app;

import org.group22.People.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

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

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                if(gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.cFactory.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.cFactory.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.cFactory.mc[tileNum1].collision || gp.cFactory.mc[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
