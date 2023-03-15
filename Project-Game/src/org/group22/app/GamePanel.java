package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.text.html.parser.Entity;

import org.group22.Drops.Item;
import org.group22.Drops.ItemFactory;
import org.group22.People.Bat;
import org.group22.People.Player;
import org.group22.GameMap.ComponentFactory;
//import org.group22.People.*;
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
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 18;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // Entity settings
    public final int maxItems = 50;
    public final int maxBats = 50;

    // FPS settings
    int fps = 60;

    // System
    public ComponentFactory cFactory;
    public KeyInputs keyInputs;
    public Thread gameThread;
    public CollisionChecker cCheck;
    public ItemFactory iFactory;

    // UI
     public UI ui;

    // Game objects
    public Player player;
    public Item[] obj;   // Array of objects
    public Bat[] bat; // Array of enemies

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState1 = 1;
    public final int playState2 = 2;
    public final int endState = 3;
    public boolean paused = false;
    public int keysNeeded;


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

        iFactory  = new ItemFactory(this);
        obj = new Item[maxItems];
        bat = new Bat[maxBats];

        gameState = titleState;
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
        if(gameState == playState1 || gameState == playState2) {
            if (paused) {
                // Pause game, do nothing
            } else if (player.dead()) {
                changeGameState(endState);
            } else {
                player.update();
                for (Bat value : bat) {
                    if (value != null) {
                        value.update();
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
    public void paintComponent(Graphics g) {    // Draw game graphics
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Title Screen
        if(gameState == titleState) {
            ui.draw(g2d);
        }
        else {
            // Tiles
            cFactory.draw(g2d);
            // Objects
            for (Item item : obj) {
                if (item != null) {
                    item.draw(g2d, this);
                }
            }
            // Enemy

            for (Bat value : bat) {
                if (value != null) {
                    value.draw(g2d);
                }
            }
            // Player
            player.draw(g2d);
            // UI
            ui.draw(g2d);
        }

        g2d.dispose();
    }

    public void changeGameState(int state) {
        System.out.println("Changing game state to " + state);
        if(state == titleState) {
            gameState = titleState;
        } else if (state == playState1) {
            cFactory = new ComponentFactory(this, "/Map/world01.txt");
            iFactory.createItem("/Map/items01.txt");
            keysNeeded = 3;
            player = new Player(this, keyInputs);
            player.setPlayerValues(35, 10, 8, "down");
            cCheck = new CollisionChecker(this);
            gameState = playState1;
        } else if (state == playState2) {
            cFactory = new ComponentFactory(this, "/Map/world02.txt");
            iFactory.createItem("/Map/items02.txt");
            keysNeeded = 1;
            player.setPlayerValues(3, 16, 8, "down");
            gameState = playState2;
        } else if (state == endState) {
            gameState = endState;
        }
    }
}
