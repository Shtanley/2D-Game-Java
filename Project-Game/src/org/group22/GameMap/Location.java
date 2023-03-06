package org.group22.GameMap;

public class Location {
    private int xCoord;
    private int yCoord;

    public Location(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Location(){}

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setLoc(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public void setLoc(Location loc){
        this.yCoord = loc.getyCoord();
        this.xCoord = loc.getxCoord();
    }
}
