package org.group22.People;

import org.group22.GameMap.Location;

public class CharacterFactory {
    public static Bat makeBat(Location loc, boolean horizontal) {
        return new Bat(loc, horizontal);
    }
    public static Skeleton makeSkeleton(Location loc) {
        return new Skeleton(loc);
    }
}
