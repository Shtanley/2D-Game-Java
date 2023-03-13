package org.group22.People;

import org.group22.GameMap.Location;

public class CharacterFactory {
    public static Hero makeHero() {
        return new Hero();
    }

    public static Bat makeBat() {
        return new Bat();
    }

    public static Bat makeBat(Location loc) {
        return new Bat(loc);
    }

    public static Skeleton makeSkeleton() {
        return new Skeleton();
    }

    public static Skeleton makeSkeleton(Location loc) {
        return new Skeleton(loc);
    }
}
