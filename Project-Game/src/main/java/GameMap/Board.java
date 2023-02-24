package GameMap;

import People.Hero;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<MapComponent>> gameMap;
    private Hero hero;

    public Board(){
        hero = new Hero();
        gameMap = new ArrayList<>();
    }

    public void updateGame(){

    }
}
