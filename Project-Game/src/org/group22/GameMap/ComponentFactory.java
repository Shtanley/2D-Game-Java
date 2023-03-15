package org.group22.GameMap;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import org.group22.app.GamePanel;

import javax.imageio.ImageIO;

/**
 * ComponentFactory class
 * Manage map component sprites and world set up
 *
 * @author Sameer
 * @author Michael
 */
public class ComponentFactory {
    GamePanel gp;
    public MapComponent[] mc;
    public int[][] mapTileNum;

    /**
     * Constructor
     *
     * @param gp the game panel
     * @param filePath path to world text file
     */
    public ComponentFactory(GamePanel gp, String filePath) {
        this.gp = gp;
        mc = new MapComponent[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap(filePath);
    }


    /**
     * Set sprite and collision for each type of map component
     */
    public void getTileImage() {
        try {
            // Grass
            mc[0] = new MapComponent();
            mc[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/grass00.png")));
            // Wall
            mc[1] = new MapComponent();
            mc[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/wall.png")));
            mc[1].collision = true;
            // Water
            mc[2] = new MapComponent();
            mc[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/water00.png")));
            mc[2].collision = true;
            // Earth
            mc[3] = new MapComponent();
            mc[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/earth.png")));
            // Trees
            mc[4] = new MapComponent();
            mc[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/tree.png")));
            mc[4].collision = true;
            // Floor
            mc[5] = new MapComponent();
            mc[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/floor01.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load map file
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read map file

            int col = 0;
            int row = 0;

            String line = br.readLine();
            while(line != null && row < gp.maxWorldRow) {
                String[] numbers = line.split(" "); // Split line into numbers
                while (col < numbers.length && col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]); // Convert string to int
                    mapTileNum[col][row] = num; // Store number in mapTileNum array
                    col++;
                }
                col = 0;
                row++;
                line = br.readLine();
            }
            br.close();

        } catch (Exception ignored) {

        }
    }

    /**
     * Draw the map
     * Draws the sprite of each map component as specified by the world text file
     *
     * @param g2d   2D graphics handler
     */
    public void draw(Graphics2D g2d) {
        int worldcol = 0;
        int worldRow = 0;

        while (worldcol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldcol][worldRow];   // Get tile number from mapTileNum array
            // Calculate x and y position of tile
            int worldX = worldcol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            // Calculate x and y position of tile on screen
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Draw tile if it is on screen to save resources
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2d.drawImage(mc[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;
                worldRow++;
            }
        }
    }

}
