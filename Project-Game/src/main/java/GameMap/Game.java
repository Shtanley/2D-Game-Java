package GameMap;

public class Game {
   private static final Board gameBoard = new Board();
   private double time;
   private int difficulty;
   private Location spawnPoint;
   private Location exitPoint;

    public Game(double time, int difficulty, Location spawnPoint, Location exitPoint) {
        this.time = time;
        this.difficulty = difficulty;
        this.spawnPoint = spawnPoint;
        this.exitPoint = exitPoint;

        setUpMap();
        setUpEnemy();
        setUpHero();
        update();
    }

    private void setUpMap(){

   }

   private void setUpEnemy(){

   }

   private void setUpHero(){

   }

   public void update(){

   }
}
