package org.group22.GameMap;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.group22.app.GamePanel;

import javax.imageio.ImageIO;

/**
 * ComponentFactory class
 * Manage component image and component movement
 * @author Sameer
 *
 * Create map components
 * @author Dina
 * @author Michael
 *
 * this class does two different things ?
 */
public class ComponentFactory {
    GamePanel gp;
    public MapComponent[] mc;
    public int mapTileNum[][];

    /**
     * Constructor
     * @param gp
     */
    public ComponentFactory(GamePanel gp) {
        this.gp = gp;
        mc = new MapComponent[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/Map/world01.txt");
    }

    /**
     * Get tile image
     */
    public void getTileImage() {
        try {
            mc[0] = new MapComponent();
            mc[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass00.png"));

            mc[1] = new MapComponent();
            mc[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            mc[1].collision = true;

            mc[2] = new MapComponent();
            mc[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water00.png"));
            mc[2].collision = true;

            mc[3] = new MapComponent();
            mc[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));

            mc[4] = new MapComponent();
            mc[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            mc[4].collision = true;

            mc[5] = new MapComponent();
            mc[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/floor01.png"));

//            mc[6] = new MapComponent();
//            mc[6].image = ImageIO.read(getClass().getResourceAsStream("/Object/door.png"));
//            mc[6].collision = true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load map file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read map file

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); // Split line into numbers
                    int num = Integer.parseInt(numbers[col]); // Convert string to int
                    mapTileNum[col][row] = num; // Store number in mapTileNum array
                    col++;
                }

                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    /**
     * Draw tile image
     * @param g2d   Graphics2D
     */
    public void draw(Graphics2D g2d) {
        // Uncomment to draw all tile types in the top left corner
        // g2d.drawImage(mc[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2d.drawImage(mc[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
        // g2d.drawImage(mc[2].image, 0, 96, gp.tileSize, gp.tileSize, null);

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

    public static Barrier makeBarrier(Location loc){
        return new Barrier(loc);
    }

    public static Barrier makeBarrier(int x, int y){
        return new Barrier(new Location(x, y));
    }

    public static Tile makeTile(Location loc){
        return new Tile(loc);
    }

    public static Tile makeTile(int x, int y){
        return new Tile(new Location(x, y));
    }

    public static Wall makeWall(Location loc){
        return new Wall(loc);
    }

    public static Wall makeWall(int x, int y){
        return new Wall(new Location(x, y));
    }
}
