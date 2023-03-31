package org.group22.People;

import org.group22.app.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Skeleton extends Enemy{

    private ArrayList<String> path;
    private int pathIndex;
    private int nextX;
    private int nextY;

    /**
     * Constructs skeleton
     * Sets skeleton's hitbox, speed, and sprite
     */
    public Skeleton(GamePanel gp) {
        super(gp);
        path = new ArrayList<>();
        pathIndex = 0;

        hitBox = new Rectangle();
        name = "Skeleton";
        speed = 1;

        // Tile size = 48, give hitbox buffer of 4 pixels
        hitBox.x = 4;
        hitBox.y = 4;
        hitBox.width = 40;
        hitBox.height = 40;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        direction = "down";

        getImage();
    }

    /**
     * Set skeleton sprite
     */
    public void getImage() {
        up1 = Entity.setupSprite("/Enemy/skeletonlord_up_1");
        up2 = Entity.setupSprite("/Enemy/skeletonlord_up_2");
        down1 = Entity.setupSprite("/Enemy/skeletonlord_down_1");
        down2 = Entity.setupSprite("/Enemy/skeletonlord_down_2");
        left1 = Entity.setupSprite("/Enemy/skeletonlord_left_1");
        left2 = Entity.setupSprite("/Enemy/skeletonlord_left_2");
        right1 = Entity.setupSprite("/Enemy/skeletonlord_right_1");
        right2 = Entity.setupSprite("/Enemy/skeletonlord_right_2");
    }

    /**
     * Determines the skeletons next direction of movement
     * Skeletons follow a patrolling movement pattern
     * i.e. they follow a set path which is specified in the enemies.txt files
     */
    public void setAction(){
        boolean condition = false;
        switch (direction){
            case "up" -> condition = worldY <= nextY;
            case "down" -> condition = worldY >= nextY;
            case "left" -> condition = worldX <= nextX;
            case "right" -> condition = worldX >= nextX;
        }
        if(condition) {
            this.direction = path.get(pathIndex);
            switch (direction){
                case "up" -> nextY = worldY - gp.tileSize;
                case "down" -> nextY = worldY + gp.tileSize;
                case "left" -> nextX = worldX - gp.tileSize;
                case "right" -> nextX = worldX + gp.tileSize;
            }
            pathIndex++;
            if(pathIndex == path.size()) {
                pathIndex = 0;
            }
        }
    }

    public void addToPath(String dir){
        assert(Objects.equals(dir, "U")
                || Objects.equals(dir, "D")
                || Objects.equals(dir, "L")
                || Objects.equals(dir, "R"));
        switch (dir) {
            case "U" -> path.add("up");
            case "D" -> path.add("down");
            case "L" -> path.add("left");
            case "R" -> path.add("right");
        }
    }

    public boolean verifyPath(){
        int deltaX = 0;
        int deltaY = 0;
        for(String dir : path) {
            switch(dir){
                case ("up") -> deltaY += 1;
                case ("down") -> deltaY -= 1;
                case ("right") -> deltaX += 1;
                case ("left") -> deltaX -= 1;
            }
        }
        return deltaX == 0 && deltaY == 0;
    }
}
