package org.group22.ui;

import java.awt.Window;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fanatasy Game");

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        System.out.println("Hello World");
    }
}