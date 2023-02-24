package People;

public class CharacterFactory {
    public static Character buildCharacter(String type){
        if (type.equals("Hero")) {
            return new Hero();
        } else if (type.equals("Bat")){
            return new Bat();
        } else if (type.equals("Skeleton")){
            return new Skeleton();
        }

        return null;
    }
}
