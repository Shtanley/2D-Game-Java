package org.group22.People;

import org.group22.app.GamePanel;

public class Skeleton extends Enemy{

    /**
     * Constructs skeleton with null location
     */
    public Skeleton(GamePanel gp) {
        super(gp);
        System.out.println("Creating " + this);
    }

    /**
     * Moves skeleton
     * Skeletons have a random movement pattern
     * @condition if skeleton moves collides with a map element, it does not move
     */
    public void move(){
//        int move = 0;
//        boolean collision = true;
//        while (collision){
//            move = (int) (Math.random() * 4) + 1;
//            collision = false;
//        }
//        if (move == 1){
//            setLoc(new Location(getLoc().getX() + 1, getLoc().getY()));
//        } else if (move == 2){
//            setLoc(new Location(getLoc().getX() - 1, getLoc().getY()));
//        } else if (move == 3){
//            setLoc(new Location(getLoc().getX(), getLoc().getY() + 1));
//        } else {
//            setLoc(new Location(getLoc().getX(), getLoc().getY() - 1));
//        }
    }

//    public String toString() {
//        return "skeleton at " + getLoc();
//    }
}
