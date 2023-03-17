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
    // Debug
    boolean checkDrawTime = false;

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

        // Title State, 3 buttons
        if(gp.gameState == gp.titleState) {
            if(key == KeyEvent.VK_UP) {
                gp.ui.titleCmdNum--;
                if(gp.ui.titleCmdNum < 0) {
                    gp.ui.titleCmdNum = 2;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gp.ui.titleCmdNum++;
                if(gp.ui.titleCmdNum > 2) {
                    gp.ui.titleCmdNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gp.ui.titleCmdNum == 0) {
                    // Start Game
                    gp.changeGameState(gp.playState1);
                }
                if(gp.ui.titleCmdNum == 1) {
                    // Settings
                    gp.changeGameState(gp.settingsState);
                }
                if(gp.ui.titleCmdNum == 2) {
                    // Exit
                    System.out.println("Exit!");
                    System.exit(0);
                }
            }
        }

        // Setting State, 4 buttons
        else if(gp.gameState == gp.settingsState) {
            if(key == KeyEvent.VK_UP) {
                gp.ui.diffCmdNum--;
                if(gp.ui.diffCmdNum < 0) {
                    gp.ui.diffCmdNum = 3;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gp.ui.diffCmdNum++;
                if(gp.ui.diffCmdNum > 3) {
                    gp.ui.diffCmdNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                gp.changeDifficulty(gp.ui.diffCmdNum);
                gp.changeGameState(gp.titleState);
            }
            if(key == KeyEvent.VK_ESCAPE) {
                gp.changeGameState(gp.titleState);
            }
        }

        // Game State
        else if(gp.gameState >= gp.playState1 && gp.gameState <= gp.playState3) {
            if (key == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P) {
                gp.paused = !gp.paused;
            }
            if (key == KeyEvent.VK_T) { // Draw time button for debugging
                checkDrawTime = !checkDrawTime;
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
