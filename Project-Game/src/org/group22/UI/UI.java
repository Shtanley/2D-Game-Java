package org.group22.UI;

import org.group22.Drops.Key;
import org.group22.app.GamePanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
/**
 * UI class
 * Manage UI elements
 * @author Sameer
 */
public class UI {
    GamePanel gp;
    Graphics2D g2d;
    Font trebuchet_40, trebuchet_80B;
    BufferedImage keyImg;
    public boolean msgOn = false;
    public String msg = "";
    int msgTimer = 0;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int cmdNum = 0;

    /**
     * UI constructor
     * @param gp    GamePanel object
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
     * @param text  Message to display
     */
    public void showMsg(String text) {
        msg = text;
        msgOn = true;
    }
    /**
     * Draw different game states to screen
     * @param g2d   Graphics2D object
     */
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(trebuchet_40);
//        g2d.setColor(Color.WHITE);
        
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState1 || gp.gameState == gp.playState2) {
            if(gp.paused) {
                drawPauseScreen();
            } else {
                drawPlayScreen();
            }
        }
        if(gp.gameState == gp.endState) {
            drawGameOverScreen();
        }

    }

    public void drawTitleScreen() {
        // Set background
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // Set font
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Fantasy Dungeon";   // Title
        int x = getHorizontalCenter(text, gp.screenWidth);
        int y = gp.tileSize * 2;
        // Text shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x+5, y+5);
        // Draw text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // Add image
        x = gp.screenWidth/2 - (gp.tileSize);
        y += gp.tileSize * 2;
        g2d.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = getHorizontalCenter(text, gp.screenWidth);
        y += gp.tileSize*4;
        g2d.drawString(text, x, y);
        if(cmdNum == 0) {
            g2d.drawString(">", x - gp.tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Settings";
        x = getHorizontalCenter(text, gp.screenWidth);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if(cmdNum == 1) {
            g2d.drawString(">", x - gp.tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Exit";
        x = getHorizontalCenter(text, gp.screenWidth);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if(cmdNum == 2) {
            g2d.drawString(">", x - gp.tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }
    }
    
    public void drawPlayScreen() {
        String text;
        int x, y;

        // Display keys and points
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("x " + gp.player.keyCount, 74, gp.tileSize*3/2);
        g2d.drawString("Points: " + gp.player.getPoints(), gp.tileSize/2, gp.tileSize*5/2);

        // Time
        playTime += (double)1/60;
        text = "Time: " + dFormat.format(playTime);
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.tileSize*3/2;
        g2d.drawString(text, x, y);

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

    public void drawPauseScreen() {
        String text;
        int x, y;

        // Draw PAUSED
        text = "PAUSED";
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.screenHeight/2;
        g2d.drawString(text, x, y);

        // Display current time
        text = "Time: " + dFormat.format(playTime);
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.tileSize;
        g2d.drawString(text, x, y);

        // Display keys and points
        g2d.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString("x " + gp.player.keyCount, 74, 65);
        g2d.drawString("Points: " + gp.player.getPoints(), gp.tileSize/2, gp.tileSize*5/2);

    }

    public void drawGameOverScreen() {
        String text;
        int x, y;

        if(gp.player.dead()) {
            // Draw Youch!
            text = "Youch!";
            g2d.setFont(trebuchet_80B);
            g2d.setColor(Color.YELLOW);
            x = getHorizontalCenter(text, gp.screenWidth);
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2d.drawString(text, x, y);
        } else {
            // Draw Escaped! :D
            text = "Escaped! :D";
            g2d.setFont(trebuchet_80B);
            g2d.setColor(Color.YELLOW);
            x = getHorizontalCenter(text, gp.screenWidth);
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2d.drawString(text, x, y);
        }

        // Display time
        text = "Time: " + dFormat.format(playTime);
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.screenHeight/2 + (gp.tileSize*4);
        g2d.drawString(text, x, y);

        // Display score
        text = "Points: " + gp.player.getPoints();
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.screenHeight/2 + (gp.tileSize*6);
        g2d.drawString(text, x, y);

        // Stop game thread
        gp.gameThread = null;
    }
    /**
     * Get horizontal center of text
     * @param text  Text to center
     * @param screenWidth   Screen width
     * @return  x coordinates of the centered text
     */
    protected int getHorizontalCenter(String text, int screenWidth) {
        int txtLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return screenWidth/2 - txtLength/2;
    }
}
