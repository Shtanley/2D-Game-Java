package org.group22.ui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    // Screen settings
    final int orgTileSize = 16;
    final int scale = 3;

    final int tileSize = orgTileSize * scale;   // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize;    // 768 pixels
    final int screenHeight = maxScreenRow * tileSize;   // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
