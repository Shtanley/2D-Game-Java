package org.group22.GameMap;

public class ComponentFactory {

    public static MapComponent buildComponent(String type, Location location){
        if (type.equals("tile")) {
            return new Tile(location);
        } else if (type.equals("barrier")){
            return new Barrier(location);
        } else if (type.equals("wall")){
            return new Wall(location);
        }

        return null;
    }

    public static Barrier makeBarrier(Location loc){
        return new Barrier(loc);
    }

    public static Barrier makeBarrier(int x, int y){
        return new Barrier(new Location(x, y));
    }

    public static Tile makeTile(Location loc){
        return new Tile(loc);
    }

    public static Tile makeTile(int x, int y){
        return new Tile(new Location(x, y));
    }

    public static Wall makeWall(Location loc){
        return new Wall(loc);
    }

    public static Wall makeWall(int x, int y){
        return new Wall(new Location(x, y));
    }


    public static Door makeDoor(Location loc){
        return new Door(loc);
    }

    public static Door makeDoor(int x, int y){
        return new Door(new Location(x, y));
    }

}
