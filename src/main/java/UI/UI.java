package UI;

import Drops.Key;
import Drops.Potion;
import People.Player;
import app.GamePanel;
import app.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * UI class
 * Manage UI elements
 *
 * @author Sameer
 * @author Michael
 * @author Dina
 */

public class UI {
    private final GamePanel gp;
    private Graphics2D g2d;
    private Font maruMonica;
    private final BufferedImage keyImg;
    private boolean msgOn = false;
    private String msg = "";
    private int msgTimer = 0;
    private double playTime;
    private final DecimalFormat dFormat = new DecimalFormat("#0.00");
    private int titleCmdNum = 0;
    private int diffCmdNum = 0;
    private final int screenWidth = GameSettings.getScreenWidth();
    private final int screenHeight = GameSettings.getScreenHeight();

    private final int tileSize = GameSettings.getTileSize();

    /**
     * UI constructor
     *
     * @param gp    GamePanel object
     */
    public UI(GamePanel gp) {
        this.gp = gp;
        //Font trebuchet_40 = new Font("Trebuchet", Font.PLAIN, 40);
        //Font trebuchet_80B = new Font("Trebuchet", Font.BOLD, 80);

        try {
            InputStream is = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
            assert is != null;
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/Font/Purisa Bold.ttf");
            assert is != null;
            //Font purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        keyImg = Key.getSprite();
        assert(keyImg != null);
    }
    /**
     * Display message on screen
     *
     * @param text  Message to display
     */
    public void showMsg(String text) {
        msg = text;
        msgOn = true;
    }
    /**
     * Draws the UI to the screen according to the current gameState
     *
     * @param g2d   Graphics2D object
     */
    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(maruMonica);

        switch (gp.getGameState()) {
            case GamePanel.titleState -> drawTitleScreen();
            case GamePanel.settingsState -> drawDifficultyScreen();
            case GamePanel.endState -> drawGameOverScreen();
        }

        if(gp.inPlayState()) {
            if (gp.isPaused()) {
                drawPauseScreen();
            } else {
                drawPlayScreen();
            }
        }

    }

    private void drawTitleScreen() {
        // Set background
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        // Set font
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Dungeon Manager Escapes";   // Title
        int x = getHorizontalCenter(text, screenWidth);
        int y = tileSize * 2;
        // Text shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x+5, y+5);
        // Draw text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // Add Player Sprite
        x = screenWidth/2 - (tileSize);
        y += tileSize * 2;
        //g2d.drawImage(Objects.requireNonNull(gp.player.getRight1()), x, y, tileSize*2, tileSize*2, null);
        g2d.drawImage(Objects.requireNonNull(Player.getSprite()), x, y, tileSize*2, tileSize*2, null);

        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize*4;
        g2d.drawString(text, x, y);
        if(titleCmdNum == 0) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Difficulty";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize;
        g2d.drawString(text, x, y);
        if(titleCmdNum == 1) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Exit";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize;
        g2d.drawString(text, x, y);
        if(titleCmdNum == 2) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }
    }

    private void drawDifficultyScreen() {
        // Set background
        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(0, 0, screenWidth, screenHeight);
        // Set font
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Difficulty";   // Title
        int x = getHorizontalCenter(text, screenWidth);
        int y = tileSize * 2;
        // Text shadow
        g2d.setColor(Color.GRAY);
        g2d.drawString(text, x+5, y+5);
        // Draw text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // Draw Potion Sprite
        x = screenWidth/2 - (tileSize * 3/2);
        y += tileSize;
        g2d.drawImage(Potion.getSprite(), x, y, tileSize*3, tileSize*3, null);


        // Menu
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Peaceful";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize*4;
        g2d.drawString(text, x, y);
        if(diffCmdNum == 0) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Easy";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize;
        g2d.drawString(text, x, y);
        if(diffCmdNum == 1) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Medium";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize;
        g2d.drawString(text, x, y);
        if(diffCmdNum == 2) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Hard";
        x = getHorizontalCenter(text, screenWidth);
        y += tileSize;
        g2d.drawString(text, x, y);
        if(diffCmdNum == 3) {
            g2d.drawString(">", x - tileSize, y);    // Use drawImage if you want to use a selection icon instead
        }
    }
    
    private void drawPlayScreen() {
        String text;
        int x, y;

        // Display keys, points, and health
        drawStats();

        // Time
        playTime += (double)1/60;
        g2d.setFont(g2d.getFont().deriveFont(40F));
        text = "Time: " + dFormat.format(playTime);
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, screenWidth);
        y = tileSize*3/2;
        g2d.drawString(text, x, y);

        // Display level and difficulty
        text = "Level: " + (gp.getGameState()-1) + "/3";
        x = screenWidth - getTextLength(text) - tileSize;
        y =  tileSize*3/2;
        g2d.drawString(text, x, y);
        text = "Difficulty: " + gp.getDifficultyString();
        x = screenWidth - getTextLength(text) - tileSize;
        y += tileSize;
        g2d.drawString(text, x, y);

        // Game messages
        if(msgOn) {
            g2d.setFont(g2d.getFont().deriveFont(27F));
            g2d.drawString(msg, tileSize/2, tileSize*5);
            msgTimer++;

            if(msgTimer > 120) {
                msgTimer = 0;
                msgOn = false;
            }
        }

        // Pause tooltip
        g2d.setFont(g2d.getFont().deriveFont(20F));
        g2d.setColor(Color.WHITE);
        text = "Press P to pause";
        x = tileSize/2;
        y = screenHeight - tileSize/2;
        g2d.drawString(text, x, y);

    }

    private void drawPauseScreen() {
        String text;
        int x, y;

        // Draw PAUSED
        text = "PAUSED";
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
        g2d.setColor(Color.WHITE);
        x = getHorizontalCenter(text, screenWidth);
        y = screenHeight/2;
        g2d.drawString(text, x, y);

        // Display current time
        g2d.setFont(g2d.getFont().deriveFont(40F));
        g2d.setColor(Color.WHITE);
        text = "Time: " + dFormat.format(playTime);
        x = getHorizontalCenter(text, screenWidth);
        y = tileSize;
        g2d.drawString(text, x, y);

        // Display health, keys and points
        drawStats();

        // Draw "return to main menu" option
        g2d.setFont(g2d.getFont().deriveFont(20F));
        g2d.setColor(Color.WHITE);
        text = "Press esc to return to main menu";
        x = tileSize/2;
        y = screenHeight - tileSize/2;
        g2d.drawString(text, x, y);
    }

    private void drawGameOverScreen() {
        String text;
        int x, y;

        // Draw title card
        if(gp.player.isDead()) {
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80F));
            // Draw Youch!
            text = "Youch!";
        } else {
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80F));
            // Draw Escaped! :D
            text = "Escaped! :D";
        }
        g2d.setColor(Color.YELLOW);
        x = getHorizontalCenter(text, screenWidth);
        y = screenHeight/2 + (tileSize*2);
        g2d.drawString(text, x, y);

        // Display time
        g2d.setFont(g2d.getFont().deriveFont(40F));
        g2d.setColor(Color.WHITE);
        text = "Time: " + dFormat.format(playTime);
        x = getHorizontalCenter(text, screenWidth);
        y = screenHeight/2 + (tileSize*4);
        g2d.drawString(text, x, y);

        // Display score
        g2d.setFont(g2d.getFont().deriveFont(40F));
        g2d.setColor(Color.WHITE);
        text = "Points: " + gp.player.getPoints();
        x = getHorizontalCenter(text, screenWidth);
        y = screenHeight/2 + (tileSize*5);
        g2d.drawString(text, x, y);

        // Display options
        // Display score
        g2d.setFont(g2d.getFont().deriveFont(30F));
        g2d.setColor(Color.WHITE);
        text = "Esc: Quit | M: Main Menu | R: Restart Game";
        x = getHorizontalCenter(text, screenWidth);
        y = screenHeight/2 - (tileSize*5);
        g2d.drawString(text, x, y);
    }

    /**
     * Get horizontal center of text
     * @param text  Text to center
     * @param screenWidth   Screen width
     * @return  x coordinates of the centered text
     */
    private int getHorizontalCenter(String text, int screenWidth) {
        int textLength = getTextLength(text);
        return screenWidth/2 - textLength/2;
    }

    private int getTextLength(String text){
        return (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
    }

    /**
     * Draws the player's keys, points, and health to the screen
     */
    private void drawStats(){
        g2d.setFont(g2d.getFont().deriveFont(48F));
        g2d.setColor(Color.WHITE);
        g2d.drawImage(keyImg, tileSize/2, tileSize/2, tileSize, tileSize, null);
        g2d.drawString(": " + gp.player.getKeyCount() + "/" + GameSettings.getKeysNeeded(), 74, tileSize*3/2);
        g2d.drawString("Points: " + gp.player.getPoints(), tileSize/2, tileSize*5/2);
        drawHealth(tileSize/2, tileSize*11/4);
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
        int heart2_x = x + tileSize*3/4;
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
        g2d.drawImage(heart1, x, y, tileSize, tileSize, null);
        g2d.drawImage(heart2, heart2_x, y, tileSize, tileSize, null);
        g2d.drawString(": " + gp.player.getHealth() + "/" + gp.player.getMaxHealth(), text_x, text_y);
    }

    public int getTitleCmdNum() {
        return titleCmdNum;
    }

    public void setTitleCmdNum(int titleCmdNum) {
        this.titleCmdNum = titleCmdNum;
    }

    public int getDiffCmdNum() {
        return diffCmdNum;
    }

    public void setDiffCmdNum(int diffCmdNum) {
        this.diffCmdNum = diffCmdNum;
    }

    public void setPlayTime(double time) {this.playTime = time;}
}
