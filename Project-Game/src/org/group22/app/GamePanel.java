package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import org.group22.Drops.Item;
import org.group22.Drops.ItemFactory;
import org.group22.People.EnemyFactory;
import org.group22.People.Enemy;
import org.group22.People.Player;
import org.group22.GameMap.ComponentFactory;
import org.group22.UI.UI;

/**
 * Game panel
 * Manage game graphics and game logic
 *
 * @author Sameer
 * @author Michael
 */
public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    public final int orgTileSize = 16; // 16x16 pixels
    public final int scale = 3;
    public final int tileSize = orgTileSize * scale;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // Entity settings
    public final int maxItems = 50;
    public final int maxEnemies = 50;

    // FPS settings
    int fps = 60;

    // System
    public Thread gameThread;
    public KeyInputs keyInputs;
    public CollisionChecker cCheck;
    public ItemFactory iFactory;
    public ComponentFactory cFactory;
    public EnemyFactory eFactory;

    // UI
     public UI ui;

    // Game objects
    public Player player;
    public Item[] obj;   // Array of objects
    public Enemy[] enemies; // Array of enemies
    public int keysNeeded;
    public int healthDrainRate;

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState1 = 1;
    public final int playState2 = 2;
    public final int playState3 = 3;
    public final int endState = 4;
    public boolean paused = false;
    public int difficulty;
    public int tickCounter = 0;

    /**
     * Game panel constructor
     * Set game panel size and background color
     * Add key inputs
     * Set focus on game panel to receive key inputs
     */
    public GamePanel() {
        ui = new UI(this);
        keyInputs = new KeyInputs(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));    // 768x576 pixels
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
        player = new Player(this, keyInputs);
        cCheck = new CollisionChecker(this);

        iFactory  = new ItemFactory(this);
        eFactory = new EnemyFactory(this);
        obj = new Item[maxItems];
        enemies = new Enemy[maxEnemies];

        gameState = titleState;
        difficulty = 0;
        healthDrainRate = 5;
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
        double timePerTick = 1000000000 / fps;   // 1000000000 nanoseconds = 1 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            // System.out.println("Game is running");
            // long currentTime = System.nanoTime();   // Get current time in nanoseconds
            // System.out.println("Current time: " + currentTime);

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timePerTick; // Get delta time
            timer += (currentTime - lastTime);    // Get time passed
            lastTime = currentTime;

            if(delta >= 1) {
                // Update game logic
                update();
                // Draw game graphics
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    /**
     * Update game logic
     * Move player
     */
    public void update() {  // Update game logic
        if(gameState == titleState) {

        }
        if(gameState >= playState1 && gameState <= playState3) {
            if (paused) {
                // Pause game, do nothing
            } else if (player.dead()) {
                changeGameState(endState);
            } else {
                tickCounter++;
                if(tickCounter >= healthDrainRate){
                    player.setHealth(-1);
                    tickCounter = 0;
                }
                player.update();
                for (Enemy enemy : enemies) {
                    if (enemy != null) {
                        enemy.update();
                    }
                }
            }
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
        if(keyInputs.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // Title Screen
        if (gameState == titleState) {
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
        if(keyInputs.checkDrawTime) {
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
        if(state == titleState) {
            gameState = titleState;
        } else if (state == playState1) {
            cFactory = new ComponentFactory(this, "/Map/world01.txt");
            iFactory.createItem("/Map/items01.txt");
            eFactory.createEnemies("/Map/enemies01.txt");
            keysNeeded = 3;
            player.setPlayerValues(35, 10, 8, "down");
            gameState = playState1;
        } else if (state == playState2) {
            cFactory = new ComponentFactory(this, "/Map/world02.txt");
            iFactory.createItem("/Map/items02.txt");
            eFactory.createEnemies("/Map/enemies02.txt");
            keysNeeded = 3;
            player.setPlayerValues(3, 16, 8, "down");
            gameState = playState2;
        } else if (state == playState3) {
            cFactory = new ComponentFactory(this, "/Map/world03.txt");
            iFactory.createItem("/Map/items03.txt");
            eFactory.createEnemies("/Map/enemies03.txt");
            keysNeeded = 6;
            player.setPlayerValues(1, 23, 8, "down");
            gameState = playState3;
        } else if (state == endState) {
            gameState = endState;
        }
    }
}
