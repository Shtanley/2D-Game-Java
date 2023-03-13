package org.group22.GameMap;

public class Location {
    private int xCoord;
    private int yCoord;

    public Location(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Location(){}

    public int getX() {
        return xCoord;
    }

    public void setX(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public void setY(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setLoc(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public void setLoc(Location loc){
        this.yCoord = loc.getY();
        this.xCoord = loc.getX();
    }

    public String toString(){
        return "(" + xCoord + ", " + yCoord + ")";
    }
}
