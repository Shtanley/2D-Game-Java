package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;

import Drops.BonusReward;
import Drops.Item;
import Drops.ItemFactory;
import Drops.Potion;
import People.EnemyFactory;
import People.Enemy;
import People.Player;
import GameMap.ComponentFactory;
import UI.UI;

/**
 * Game panel
 * Manage game graphics and game logic
 *
 * @author Sameer
 * @author Michael
 * @author Dina
 */
public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    // FPS settings
    int fps = 60;

    // System
    public Thread gameThread;
    public long timer;
    private final KeyInputs keyInputs;
    public CollisionChecker cCheck;
    private ItemFactory iFactory;
    public ComponentFactory cFactory;

    private EnemyFactory eFactory;

    // UI
    public UI ui;

    // Game objects
    public Player player;
    public Item[] obj;   // Array of objects
    public final Vector<BonusReward> tempItems = new Vector<>(); // Vector of temporary items
    public Enemy[] enemies; // Array of enemies

    // Game state
    private int gameState;
    public static final int titleState = 0;
    public static final int settingsState = 1;
    public static final int playState1 = 2;
    public static final int playState2 = 3;
    public static final int playState3 = 4;
    public static final int endState = 5;
    private boolean paused = false;
    private int difficulty;

    // Tick counters
    private int healthTickCounter = 0;
    private int potionSpawnTickCounter = 0;

    /**
     * Game panel constructor
     * Set game panel size and background color
     * Add key inputs
     * Set focus on game panel to receive key inputs
     */
    public GamePanel() {
        ui = new UI(this);
        keyInputs = new KeyInputs(this);
        player = new Player(this, keyInputs);
        cCheck = new CollisionChecker(this);
        cFactory = new ComponentFactory(this);
        iFactory  = new ItemFactory(this);
        eFactory = new EnemyFactory(this);

        this.setPreferredSize(new Dimension(GameSettings.getScreenWidth(), GameSettings.getScreenHeight()));    // 768x576 pixels
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);   // Double buffering
        this.addKeyListener(keyInputs); // Add key inputs
        this.setFocusable(true);    // Focus on JPanel to receive key inputs
    }
    
    /**
     * Setup game
     * Create game objects
     * Set game state
     */
    public void setupGame() {
        gameState = titleState;
        changeDifficulty(1);
    }
    
    /**
     * Start game thread
     * Create new thread
     * Start thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * Run game thread
     * Update game logic
     * Draw game graphics
     * Calculate FPS
     */
    @Override
    public void run() { // Delta time method
        double timePerTick = 1000000000.0 / fps;   // 1000000000 nanoseconds = 1 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            timer = currentTime / 1000000000;
            delta += (currentTime - lastTime) / timePerTick; // Get delta time
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    /**
     * Update game logic
     * Drain player's health
     * Handle spawning/de-spawning of temporary items
     * Move player
     */
    public void update() {  // Update game logic
        if(inPlayState() && !isPaused()) {
            if (player.isDead()) {
                changeGameState(endState);
            }
            healthTickCounter++;
            potionSpawnTickCounter++;
            // Drain health
            drainHealth();
            // Attempt to spawn bonus reward
            attemptSpawnPotion();
            // De-spawn expired temporary items
            tempItems.removeIf(bonus -> (timer > bonus.getBirthTime() + bonus.getLifetime()));
            // Update player
            player.update();
            // Update enemies
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.update();
                }
            }

        }
            
    }

    /**
     * If the difficult is greater than 0 (not on peaceful mode),
     * decreases the player's health by 1 every healthDrainRate ticks
     */
    private void drainHealth(){
        if(difficulty > 0) {
            if (healthTickCounter >= GameSettings.getHealthDrainRate()) {
                player.adjustHealth(-1);
                healthTickCounter = 0;
            }
        }
    }

    /**
     * Every time a fixed amount of ticks passes, given by the Potion classes spawnTimer variable,
     * attempts to spawn a potion.
     * If there is less than maxTempItems (given by GameSettings) in the game then there is a
     * random chance of successfully spawning the potion.
     * The probability is given by the Potion class's spawnChance variable.
     * In this case, a potion is spawned at a random location within 10 tiles of the player.
     * This is handled by the ItemFactory::spawnPotion method.
     */
    private void attemptSpawnPotion(){
        if(potionSpawnTickCounter >= Potion.getSpawnTimer()) {
            if(tempItems.size() < GameSettings.getMaxTempItems()) {
                //System.out.println("Attempting to spawn potion");
                Random rand = new Random();
                if (rand.nextDouble() < Potion.getSpawnChance()) {
                    // Successfully spawns potion
                    tempItems.add(iFactory.spawnPotion());
                }
            }
            potionSpawnTickCounter = 0;
        }
    }

    /**
     * Draw game graphics
     * Draw map components first
     * Draw player in the 2nd layer on top of the tiles
     */
    @Override
    public void paintComponent(Graphics g) {    // Draw game graphics
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Debug
        long drawStart = 0;
        if(keyInputs.isCheckDrawTime()) {
            drawStart = System.nanoTime();
        }

        // Title Screen
        if (gameState == titleState || gameState == settingsState) {
            ui.draw(g2d);
        } else {
            // Tiles
            cFactory.draw(g2d);
            // Objects
            for (Item item : obj) {
                if (item != null) {
                    item.draw(g2d, this);
                }
            }
            // Bonus Rewards
            synchronized (tempItems) {
                for (BonusReward bonus : tempItems) {
                    bonus.draw(g2d, this);
                }
            }
            // Enemy
            for (Enemy enemy : enemies) {
                if (enemy != null) {
                    enemy.draw(g2d);
                }
            }
            // Player
            player.draw(g2d);
            // UI
            ui.draw(g2d);
        }

        // Debug
        if(keyInputs.isCheckDrawTime()) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2d.dispose();
    }

    /**
     * Handles all changes in game states
     *
     * @param state the state to change the game to
     */
    public void changeGameState(int state) {
        System.out.println("Changing game state to " + state);
        switch (state) {
            case playState1 -> {
                ui.setPlayTime(0);
                player.resetPlayer();
                setupLevel("01");
                player.setPlayerValues(14, 15, 8, "down");
            }
            case playState2 -> {
                setupLevel("02");
                player.setPlayerValues(3, 16, 8, "right");
            }
            case playState3 -> {
                setupLevel("03");
                player.setPlayerValues(1, 23, 8, "down");
            }
        }
        gameState = state;
    }

    /**
     * Sets up a given level
     * Loads the map, spawns items and enemies, resets temporary items, and sets the number of keys needed
     *
     * @param levelID ID of the level to set up
     */
    public void setupLevel(String levelID) {
        cFactory.loadMap("/Map/world" + levelID + ".txt");
        iFactory.createItems("/Map/items" + levelID + ".txt");
        eFactory.createEnemies("/Map/enemies" + levelID + ".txt");
        tempItems.clear();
        GameSettings.setKeysNeeded(iFactory.getNumKeys());
    }

    /**
     * Changes the difficulty of the game, setting the healthDrainRate accordingly
     * For hard mode, also adjusts potionSpawnTimer, potionSpawnChance, and potionLifetime
     *
     * @param newDifficulty the difficulty to change to
     */
    public void changeDifficulty(int newDifficulty){
        difficulty = newDifficulty;
        switch (difficulty) {
            case(0) -> GameSettings.setHealthDrainRate(-1);
            case(1) -> GameSettings.setHealthDrainRate(30);
            case(2) -> GameSettings.setHealthDrainRate(20);
            case(3) -> {
                GameSettings.setHealthDrainRate(10);
                // Increase the amount of potions to compensate for difficult health drain,
                // but also decreases their lifetime
                Potion.setPotionSpawnTimer(50);
                Potion.setPotionSpawnChance(0.3);
                Potion.setPotionLifetime(3);
            }
        }
        ui.setDiffCmdNum(difficulty);
        System.out.println("New difficulty: " + difficulty);
    }

    public void endGame(){
        System.out.println("Exit!");
        gameThread = null;
        System.exit(0);
    }

    public boolean inPlayState(){
        return gameState >= playState1 && gameState <= playState3;
    }

    public boolean isPaused(){
        return paused;
    }

    public void setPaused(boolean pauseStatus) {
        paused = pauseStatus;
    }

    public int getGameState(){
        return gameState;
    }

    public KeyInputs getKeyInputs() {
        return keyInputs;
    }

    public ItemFactory getiFactory() {
        return iFactory;
    }

    public EnemyFactory geteFactory() {
        return eFactory;
    }
}
