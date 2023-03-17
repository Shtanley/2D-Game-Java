package org.group22.UI;

import org.group22.Drops.Key;
import org.group22.app.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * UI class
 * Manage UI elements
 * @author Sameer
 */
public class UI {
    GamePanel gp;
    Graphics2D g2d;
    Font trebuchet_40, trebuchet_80B;
    BufferedImage keyImg, healthImg, healthFull, healthEmpty, healthHalf;
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
//        healthImg = setHealthImg(gp.getPlayerHealth()); // gp.player is null here
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
        if(gp.gameState >= gp.playState1 && gp.gameState <= gp.playState3) {
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

        // Display keys and points and health
        drawStats();

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
        g2d.setFont(trebuchet_40.deriveFont(27F));
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.tileSize;
        g2d.drawString(text, x, y);

        // Display health, keys and points
        drawStats();
    }

    public void drawGameOverScreen() {
        String text;
        int x, y;

        if(gp.player.dead()) {
            // Draw Youch!
            text = "Youch!";
        } else {
            // Draw Escaped! :D
            text = "Escaped! :D";
        }
        g2d.setFont(trebuchet_80B);
        g2d.setColor(Color.YELLOW);
        x = getHorizontalCenter(text, gp.screenWidth);
        y = gp.screenHeight/2 + (gp.tileSize*2);
        g2d.drawString(text, x, y);

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

    public void drawStats(){
        g2d.setFont(trebuchet_40);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(keyImg, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2d.drawString(": " + gp.player.keyCount + "/" + gp.keysNeeded, 74, gp.tileSize*3/2);
        g2d.drawString("Points: " + gp.player.getPoints(), gp.tileSize/2, gp.tileSize*5/2);
        drawHealth(gp.tileSize/2, gp.tileSize*11/4);
    }

    /**
     * Draw heart images onto screen based on player health
     * @param x    x coordinate to draw hearts at
     * @param y    y coordinate to draw hearts at
     */
    public void drawHealth(int x, int y) {
        int health = gp.player.getHealth();
        // Set x & y coordinates for heart images
        BufferedImage heart1;
        BufferedImage heart2;
        int heart1_x = x;
        int heart1_y = y;
        int heart2_x = x + gp.tileSize*3/4;
        int heart2_y = y;
        int text_x = x + 90;
        int text_y = y + 40;
        // Draw first heart
        if(health >= 100) {
            heart1 = gp.player.getHeartSprite(10);
        } else if(health >= 90) {
            heart1 = gp.player.getHeartSprite(9);
        } else if(health >= 80) {
            heart1 = gp.player.getHeartSprite(8);
        } else if(health >= 70) {
            heart1 = gp.player.getHeartSprite(7);
        } else if(health >= 60) {
            heart1 = gp.player.getHeartSprite(6);
        } else if(health >= 50) {
            heart1 = gp.player.getHeartSprite(5);
        } else if(health >= 40) {
            heart1 = gp.player.getHeartSprite(4);
        } else if(health >= 30) {
            heart1 = gp.player.getHeartSprite(3);
        } else if(health >= 20) {
            heart1 = gp.player.getHeartSprite(2);
        } else if(health >= 10) {
            heart1 = gp.player.getHeartSprite(1);
        } else if(health > 0) {
            heart1 = gp.player.getHeartSprite(0);
        } else {//health <= 0
            heart1 = gp.player.getHeartSprite(-1);
        }
        // Draw second heart
        if(health == 200) {
            heart2 = gp.player.getHeartSprite(10);
        } else if(health >= 190) {
            heart2 = gp.player.getHeartSprite(9);
        } else if(health >= 180) {
            heart2 = gp.player.getHeartSprite(8);
        } else if(health >= 170) {
            heart2 = gp.player.getHeartSprite(7);
        } else if(health >= 160) {
            heart2 = gp.player.getHeartSprite(6);
        } else if(health >= 150) {
            heart2 = gp.player.getHeartSprite(5);
        } else if(health >= 140) {
            heart2 = gp.player.getHeartSprite(4);
        } else if(health >= 130) {
            heart2 = gp.player.getHeartSprite(3);
        } else if(health >= 120) {
            heart2 = gp.player.getHeartSprite(2);
        } else if(health >= 110) {
            heart2 = gp.player.getHeartSprite(1);
        } else if(health > 100) {
            heart2 = gp.player.getHeartSprite(0);
        } else { //health <= 100
            heart2 = gp.player.getHeartSprite(-1);
        }
        g2d.drawImage(heart1, heart1_x, heart1_y, gp.tileSize, gp.tileSize, null);
        g2d.drawImage(heart2, heart2_x, heart2_y, gp.tileSize, gp.tileSize, null);
        g2d.drawString(": " + gp.player.getHealth() + "/" + gp.player.getMaxHealth(), text_x, text_y);
    }
}
