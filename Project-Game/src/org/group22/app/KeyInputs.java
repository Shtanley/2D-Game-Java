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
     * Check which key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

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
            if(gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            } 
            // System.out.println("ESCAPE");
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
