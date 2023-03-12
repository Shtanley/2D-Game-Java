package org.group22.UI;

import org.group22.Drops.Key;
import org.group22.app.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
/**
 * UI class
 * Manage UI elements
 * @author Sameer
 */
public class UI {
    GamePanel gp;
    Font trebuchet_40, trebuchet_80B;
    BufferedImage keyImg;
    public boolean msgOn = false;
    public String msg = "";
    int msgTimer = 0;
    public boolean gameOver = false;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    /**
     * UI constructor
     * @param gp
     */
    public UI(GamePanel gp) {
        this.gp = gp;
        trebuchet_40 = new Font("Trebuchet", Font.PLAIN, 40);
        trebuchet_80B = new Font("Trebuchet", Font.BOLD, 80);

        Key key = new Key();
        keyImg = key.image;
    }
    /**
     * Display message on screen
     * @param text
     */
    public void showMsg(String text) {
        msg = text;
        msgOn = true;
    }
    /**
     * Draw different game states to screen
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        if(gp.playState == gp.titleState) {
            drawTitleScreen(g2d);
        }
        if(gameOver) {
            drawGameOverScreen(g2d);
        }
        if(gp.gameState == gp.playState) {
            drawPlayScreen(g2d);
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen(g2d);
        }

    }

    public void drawTitleScreen(Graphics2D g2d) {
        String title = "Fantasy Dungeon";   // Title
        // Set font and color
        g2d.setFont(trebuchet_80B.deriveFont(Font.PLAIN, 20 * gp.scale));
        g2d.setColor(Color.WHITE);
        // Draw title
        int x = getHorizontalCenter(title, g2d, gp.screenWidth);
        int y = gp.tileSize * 2;
        g2d.drawString(title, x, y);
    }
    
    public void drawPlayScreen(Graphics2D g2d) {
        // Set font and color
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        // Draw key count
        g2d.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("x " + gp.player.keyCount, 74, 65);

        // Time
        playTime += (double)1/60;
        g2d.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
        // Draw message
        if(msgOn) {
            g2d.setFont(g2d.getFont().deriveFont(27F));
            g2d.drawString(msg, gp.tileSize/2, gp.tileSize*5);
            msgTimer++;

            if(msgTimer > 120) {
                msgTimer = 0;
                msgOn = false;
            }
        }
    }

    public void drawPauseScreen(Graphics2D g2d) {
        // Set font and color
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED"; // Text to draw
        // Draw text
        int x = getHorizontalCenter(text, g2d, gp.screenWidth);
        int y = gp.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    public void drawGameOverScreen(Graphics2D g2d) {
        String text;
        int txtLength, x, y;
        // Set font and color
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        text = "Time: " + dFormat.format(playTime); // Draw time
        txtLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gp.screenWidth/2 - txtLength/2;
        y = gp.screenHeight/2 + (gp.tileSize*4);
        g2d.drawString(text, x, y); // Draw text
        // Set font and color
        g2d.setFont(trebuchet_80B);
        g2d.setColor(Color.YELLOW);
        text = "Game Over"; // Draw game over
        txtLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gp.screenWidth/2 - txtLength/2;
        y = gp.screenHeight/2 + (gp.tileSize*2);
        g2d.drawString(text, x, y); // Draw text

        gp.gameThread = null;   // Stop game thread
    }
    /**
     * Get horizontal center of text
     * @param text
     * @param g2d
     * @param screenWidth
     * @return
     */
    protected int getHorizontalCenter(String text, Graphics2D g2d, int screenWidth) {
        int txtLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return screenWidth/2 - txtLength/2;
    }
}
