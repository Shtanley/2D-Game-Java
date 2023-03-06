package org.group22.People;

import org.group22.Drops.Item;

public class Hero extends java.lang.Character {
    private final static int maxHealth = 100;
    private int health;
    private int points;

    public Hero() {
        super();
        this.health = maxHealth;
        this.points = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void move(Direction d){

    }

    public void move(){

    }

    public void collectItem(Item items){

    }
}
