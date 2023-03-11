package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.Key;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

import org.group22.Drops.Item;
import org.group22.Drops.ItemFactory;
import org.group22.GameMap.ComponentFactory;
import org.group22.People.*;

/**
 * Game panel
 * Manage game graphics and game logic
 * @author Sameer
 */
public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int orgTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public int tileSize = orgTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = maxScreenCol * tileSize;
    public int screenHeight = maxScreenRow * tileSize;

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // FPS settings
    int fps = 60;

    ComponentFactory cFactory = new ComponentFactory(this);
    KeyInputs keyInputs = new KeyInputs();
    Thread gameThread;
    public CollisionChecker cCheck = new CollisionChecker(this);
    public ItemFactory iFactory = new ItemFactory(this);
    public Player player = new Player(this, keyInputs);
    public Item obj[] = new Item[10];   // Array of objects

    /**
     * Game panel constructor
     * Set game panel size and background color
     * Add key inputs
     * Set focus on game panel to receive key inputs
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));    // 768x576 pixels
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);   // Double buffering
        this.addKeyListener(keyInputs); // Add key inputs
        this.setFocusable(true);    // Focus on JPanel to receive key inputs
    }

    public void setupGame() {
        iFactory.createItem();
    }
    
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
        player.update();
    }

    /**
     * Draw game graphics
     * Draw map components first
     * Draw player in the 2nd layer on top of the tiles
     */
    public void paintComponent(Graphics g) {    // Draw game graphics
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        cFactory.draw(g2d);

        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2d, this);
            }
        }

        player.draw(g2d);
        g2d.dispose();
    }
}
