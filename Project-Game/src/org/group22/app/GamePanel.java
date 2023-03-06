package org.group22.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));    // 768x576 pixels
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);   // Double buffering
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null) {
            // System.out.println("Game is running");

            // Update game logic
            update();

            // Draw game graphics
            repaint();
        }
    }

    public void update() {
        // Update game logic
    }

    public void paintComponent(Graphics g) {
        // Draw game graphics
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(100, 100, tileSize, tileSize);
        g2d.dispose();
    }
}
