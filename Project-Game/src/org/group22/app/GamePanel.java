package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.Key;
import javax.swing.JPanel;
import javax.swing.text.PlainDocument;

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

    public int tileSize = orgTileSize * scale;   // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;    // 768 pixels
    final int screenHeight = maxScreenRow * tileSize;   // 576 pixels

    // FPS settings
    int fps = 60;

    KeyInputs keyInputs = new KeyInputs();
    Thread gameThread;
    Player player = new Player(this, keyInputs);

    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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
     * Draw player
     */
    public void paintComponent(Graphics g) {    // Draw game graphics
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
        g2d.dispose();
    }
}
