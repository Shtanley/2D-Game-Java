package org.group22.GameMap;

public class ComponentFactory {

    public static MapComponent buildComponent(String type, Location location){
        if (type.equals("tile")) {
            return new Tiles(location);
        } else if (type.equals("barrier")){
            return new Barriers(location);
        } else if (type.equals("wall")){
            return new Wall(location);
        }

        return null;
    }
}
