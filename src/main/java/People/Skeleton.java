package People;

import app.GamePanel;
import app.GameSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Skeleton class
 * Manage skeleton image and skeleton movement
 * @author Dina
 * @author Michael
 * @author Sameer
 */
public class Skeleton extends Enemy{

    private final ArrayList<String> path;
    private int pathIndex;
    private int nextX;
    private int nextY;

    /**
     * Constructs skeleton
     * Sets skeleton's hit box, speed, and sprite
     */
    public Skeleton(GamePanel gp) {
        super(gp);
        path = new ArrayList<>();
        pathIndex = 0;

        // Tile size = 48, give hit box buffer of 4
        setHitBox(new Rectangle(4, 4, 40, 40));
        setSpeed(1);

        setHitBoxDefaultX(getHitBox().x);
        setHitBoxDefaultY(getHitBox().y);

        setDirection("down");

        getImage();
    }

    /**
     * Set skeleton sprite
     */

    public void getImage() {
        setUp1(setupSprite("/Enemy/skeletonlord_up_1"));
        setUp2(setupSprite("/Enemy/skeletonlord_up_2"));
        setDown1(setupSprite("/Enemy/skeletonlord_down_1"));
        setDown2(setupSprite("/Enemy/skeletonlord_down_2"));
        setLeft1(setupSprite("/Enemy/skeletonlord_left_1"));
        setLeft2(setupSprite("/Enemy/skeletonlord_left_2"));
        setRight1(setupSprite("/Enemy/skeletonlord_right_1"));
        setRight2(setupSprite("/Enemy/skeletonlord_right_2"));
    }

    /**
     * Determines the skeletons next direction of movement
     * Skeletons follow a patrolling movement pattern
     * i.e. they follow a set path which is specified in the enemies.txt files
     */
    public void setAction(){
        boolean condition = false;
        switch (getDirection()){
            case "up" -> condition = getWorldY() <= nextY;
            case "down" -> condition = getWorldY() >= nextY;
            case "left" -> condition = getWorldX() <= nextX;
            case "right" -> condition = getWorldX() >= nextX;
        }
        if(condition) {
            setDirection(path.get(pathIndex));
            int tileSize = GameSettings.getTileSize();
            switch (getDirection()){
                case "up" -> nextY = getWorldY() - tileSize;
                case "down" -> nextY = getWorldY() + tileSize;
                case "left" -> nextX = getWorldX() - tileSize;
                case "right" -> nextX = getWorldX() + tileSize;
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
