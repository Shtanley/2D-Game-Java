package org.group22.People;

import org.group22.app.*;

import java.awt.*;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Player class
 * Manage player image and player movement
 * @author Sameer
 */
public class Player extends Entity {
    GamePanel gp;
    KeyInputs keyInputs;

    /**
     * Player constructor
     * Set default values
     * Get player image
     * @param gp
     * @param keyIn
     */
    public Player(GamePanel gp, KeyInputs keyIn) {
        this.gp = gp;
        this.keyInputs = keyIn;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    /**
     * Get player image
     * @throws IOException
     */
    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update player position
     * Update player image
     */
    public void update() {
        if(keyInputs.upPressed || keyInputs.downPressed || keyInputs.leftPressed || keyInputs.rightPressed) {
            if (keyInputs.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyInputs.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyInputs.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if (keyInputs.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCount++;
            if(spriteCount > 10) {
                spriteCount = 0;
                if(spriteNum == 1)
                    spriteNum = 2;
                else
                    spriteNum = 1;
            }
        }
    }

    /**
     * Draw player image
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        // Easter egg idea: player image is a white square
        // g.setColor(Color.WHITE);
        // g.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else
                    image = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else
                    image = right2;
                break;
        }

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
