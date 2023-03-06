package org.group22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manage key inputs from keyboard
 */
public class KeyInputs implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            upPressed = true;
            System.out.println("UP");
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
            System.out.println("DOWN");
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
            System.out.println("LEFT");
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            System.out.println("RIGHT");
        }
    }

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
