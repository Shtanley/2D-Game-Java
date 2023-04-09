import app.GamePanel;

import javax.swing.JFrame;



/**
 * Main class
 * Create game window
 * Add game panel to game window
 * Set game window location
 * Set game window visible
 * 
 * @author Sameer
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fantasy Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
        gamePanel.changeGameState(3);
    }
}