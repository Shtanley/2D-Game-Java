package org.group22.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
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

    public int mapWidth;
    public int mapHeight;

    /**
     * Constructor
     *
     * @param gp the game panel
     */
    public ComponentFactory(GamePanel gp) {
        this.gp = gp;
        mc = new MapComponent[10];
        getTileImages();
    }


    /**
     * Set sprite and collision for each type of map component
     */
    public void getTileImages() {
        // ground normal
        mc[0] = new MapComponent();
        mc[0].image = setup("/Tilesv2/ground01");
        // ground dark
        mc[1] = new MapComponent();
        mc[1].image = setup("/Tilesv2/ground02");
        mc[1].collision = true;
        // barrier horizontal or wall up
        mc[2] = new MapComponent();
        mc[2].image = setup("/Tilesv2/barrier01");
        mc[2].collision = true;
        // barrier vertical
        mc[3] = new MapComponent();
        mc[3].image = setup("/Tilesv2/barrier02");
        mc[3].collision = true;
        // wall left
        mc[4] = new MapComponent();
        mc[4].image = setup("/Tilesv2/barrier03");
        mc[4].collision = true;
        // wall corner
        mc[5] = new MapComponent();
        mc[5].image = setup("/Tilesv2/barrier04");
        mc[5].collision = true;
        // wall down
        mc[6] = new MapComponent();
        mc[6].image = setup("/Tilesv2/barrier05");
        mc[6].collision = true;
        // barrier corner left down
        mc[7] = new MapComponent();
        mc[7].image = setup("/Tilesv2/barriercorner01");
        mc[7].collision = true;
        // barrier corner right up
        mc[8] = new MapComponent();
        mc[8].image = setup("/Tilesv2/barriercorner02");
        mc[8].collision = true;

    }

    public void loadMap(String filePath) {
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapWidth = 0;
        mapHeight = 0;
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
                    if(mapWidth < col){mapWidth = col;}
                    col++;
                }
                col = 0;
                if(mapHeight < row){mapHeight = row;}
                row++;
                line = br.readLine();
            }
            br.close();
            mapWidth++;
            mapHeight++;
        } catch (Exception ignored) {

        }
        System.out.println("Map loaded: (" + mapWidth + ", " + mapHeight + ")");
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

    public BufferedImage scaleImg(BufferedImage original, int width, int height) {
        BufferedImage newImg = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();
        return newImg;
    }

    public BufferedImage setup(String imgPath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imgPath + ".png")));
//            image = scaleImg(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
