package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.Key;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int orgTileSize = 16; // 16x16 pixels
    final int scale = 3;

    final int tileSize = orgTileSize * scale;   // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;    // 768 pixels
    final int screenHeight = maxScreenRow * tileSize;   // 576 pixels

    // FPS settings
    int fps = 60;

    KeyInputs keyInputs = new KeyInputs();
    Thread gameThread;

    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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

    // @Override
    // public void run() { // Sleep method
    //    double timePerTick = 1000000000 / fps;   // 1000000000 nanoseconds = 1 second
    //    double nextTick = System.nanoTime() + timePerTick; // Get current time in nanoseconds

    //     while(gameThread != null) {
    //        // System.out.println("Game is running");
    //        // long currentTime = System.nanoTime();   // Get current time in nanoseconds
    //        // System.out.println("Current time: " + currentTime);

    //        // Update game logic
    //        update();
    //        // Draw game graphics
    //        repaint();

    //         try {
    //            double remainingTime = nextTick - System.nanoTime(); // Get remaining time in nanoseconds
    //            remainingTime /= 1000000;   // Convert to milliseconds

    //             if(remainingTime < 0) {
    //                remainingTime = 0;
    //             }

    //            Thread.sleep((long) remainingTime); // Sleep for remaining time
    //            nextTick += timePerTick;    // Update next tick
    //         } catch (InterruptedException e) {
    //            e.printStackTrace();
    //         }
    //     }
    // }
    
    @Override
    public void run() { // Delta time method
        double timePerTick = 1000000000 / fps;   // 1000000000 nanoseconds = 1 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            // System.out.println("Game is running");
            // long currentTime = System.nanoTime();   // Get current time in nanoseconds
            // System.out.println("Current time: " + currentTime);

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / timePerTick; // Get delta time
            lastTime = currentTime;

            if(delta >= 1) {
                // Update game logic
                update();
                // Draw game graphics
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // Update game logic
        if (keyInputs.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyInputs.downPressed) {
            playerY += playerSpeed;
        }
        if (keyInputs.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyInputs.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        // Draw game graphics
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
