package org.group22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manage key inputs from keyboard
 * 
 * @author Sameer
 */
public class KeyInputs implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyInputs(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {  // Not used
    }

    /**
     * Check which key is pressed in the menu
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Title State
        if(gp.gameState == gp.titleState) {
            if(key == KeyEvent.VK_UP) {
                gp.ui.cmdNum--;
                System.out.println("UP");
                if(gp.ui.cmdNum < 0) {
                    gp.ui.cmdNum = 2;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gp.ui.cmdNum++;
                System.out.println("DOWN");
                if(gp.ui.cmdNum > 2) {
                    gp.ui.cmdNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gp.ui.cmdNum == 0) {
                    // Start Game
                    gp.changeGameState(gp.playState1);
                }
                if(gp.ui.cmdNum == 1) {
                    // Settings
                    System.exit(0); // exit game for now
                }
                if(gp.ui.cmdNum == 2) {
                    // Exit
                    System.exit(0);
                }
            }
        }
        // Game State, player movement
        if(gp.gameState == gp.playState1 || gp.gameState == gp.playState2) {
            if (key == KeyEvent.VK_UP) {
                upPressed = true;
                // System.out.println("UP");
            }
            if (key == KeyEvent.VK_DOWN) {
                downPressed = true;
                // System.out.println("DOWN");
            }
            if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
                // System.out.println("LEFT");
            }
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = true;
                // System.out.println("RIGHT");
            }
            if (key == KeyEvent.VK_ESCAPE) {
                gp.paused = !gp.paused;
                // System.out.println("ESCAPE");
            }
        }
    }

    /**
     * Check which key is released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
