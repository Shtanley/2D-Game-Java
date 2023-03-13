package org.group22.Drops.People;

import org.group22.GameMap.Location;

public class CharacterFactory {
    public static Bat makeBat(Location loc) {
        return new Bat(loc);
    }
    public static Skeleton makeSkeleton(Location loc) {
        return new Skeleton(loc);
    }
}
