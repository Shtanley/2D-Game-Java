package GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import app.GamePanel;
import app.GameSettings;

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
    public BufferedImage[][] mapTileImage;

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
        mc[0].setImage(setup("/Tilesv2/ground01"));
        // ground dark
        mc[1] = new MapComponent();
        mc[1].setImage(setup("/Tilesv2/ground02"));
        mc[1].setCollisionOn();
        // barrier horizontal or wall up
        mc[2] = new MapComponent();
        mc[2].setImage(setup("/Tilesv2/barrier01"));
        mc[2].setCollisionOn();
        // barrier vertical
        mc[3] = new MapComponent();
        mc[3].setImage(setup("/Tilesv2/barrier02"));
        mc[3].setCollisionOn();
        // wall left
        mc[4] = new MapComponent();
        mc[4].setImage(setup("/Tilesv2/barrier03"));
        mc[4].setCollisionOn();
        // wall corner
        mc[5] = new MapComponent();
        mc[5].setImage( setup("/Tilesv2/barrier04"));
        mc[5].setCollisionOn();
        // wall down
        mc[6] = new MapComponent();
        mc[6].setImage(setup("/Tilesv2/barrier05"));
        mc[6].setCollisionOn();
        // barrier corner left down
        mc[7] = new MapComponent();
        mc[7].setImage(setup("/Tilesv2/barriercorner01"));
        mc[7].setCollisionOn();
        // barrier corner right up
        mc[8] = new MapComponent();
        mc[8].setImage(setup("/Tilesv2/barriercorner02"));
        mc[8].setCollisionOn();

    }

    public void loadMap(String filePath) {
        int maxWorldCol = GameSettings.getMaxWorldCol();
        int maxWorldRow = GameSettings.getMaxWorldRow();

        mapTileNum = new int[maxWorldCol][maxWorldRow];
        mapWidth = 0;
        mapHeight = 0;

        System.out.println("Loading map: " + filePath);
        fillMapTileNum(filePath, maxWorldRow, maxWorldCol);
        fillMapTileImage(maxWorldRow, maxWorldCol);
    }

    private void fillMapTileNum(String filePath, int maxWorldRow, int maxWorldCol){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load map file
            if(is == null) {
                throw new RuntimeException();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read map file

            int col = 0;
            int row = 0;

            String line = br.readLine();
            while(line != null && row < maxWorldRow) {
                String[] numbers = line.split(" "); // Split line into numbers
                while (col < numbers.length && col < maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]); // Convert string to int
                    mapTileNum[col][row] = num; // Store number in mapTileNum array
                    col++;
                }
                if(mapWidth < col){mapWidth = col;}
                col = 0;
                row++;
                line = br.readLine();
            }
            br.close();
            mapHeight = row;
        } catch (Exception ignored) {
            System.out.println("Failed to load map contents from " + filePath);
            return;
        }
        System.out.println("Map loaded: width = " + mapWidth + ", height = " + mapHeight);
    }

    private void fillMapTileImage(int maxWorldRow, int maxWorldCol){
        mapTileImage = new BufferedImage[maxWorldRow][maxWorldCol];
        for(int i = 0; i < maxWorldRow; i++){
            for(int j = 0; j < maxWorldCol; j++){
                int tileNum = mapTileNum[i][j];
                mapTileImage[i][j] = mc[tileNum].getImage();
            }
        }
    }

    /**
     * Draw the map
     * Draws the sprite of each map component as specified by the world text file
     *
     * @param g2d   2D graphics handler
     */
    public void draw(Graphics2D g2d) {
        int tileSize = GameSettings.getTileSize();
        int maxWorldCol = GameSettings.getMaxWorldCol();
        int maxWorldRow = GameSettings.getMaxWorldRow();
        int tileBuffer = 2;

        // Calculate the border of the screen
        // In world coordinates
        int minX = gp.player.getWorldX() - gp.player.getScreenX();
        int maxX = gp.player.getWorldX() + gp.player.getScreenX();
        int minY = gp.player.getWorldY() - gp.player.getScreenY();
        int maxY = gp.player.getWorldY() + gp.player.getScreenY();
        // Convert to tile coordinates and add buffer
        int minCol = minX / tileSize - tileBuffer;
        int maxCol = maxX / tileSize + tileBuffer;
        int minRow = minY / tileSize - tileBuffer;
        int maxRow = maxY / tileSize + tileBuffer;
        // Limit to world bounds
        if(minCol < 0) {
            minCol = 0;
        }
        if(maxCol > maxWorldCol){
            maxCol = maxWorldCol;
        }
        if(minRow < 0) {
            minRow = 0;
        }
        if(maxRow > maxWorldRow){
            maxRow = maxWorldRow;
        }

        int tileScreenX;
        int tileScreenY;

        for(int i = minCol; i < maxCol; i++) {
            for(int j = minRow; j < maxRow; j++) {
                // Location of the tile in the screen's coordinates
                tileScreenX = i * tileSize - minX;
                tileScreenY = j * tileSize - minY;
                g2d.drawImage(mapTileImage[i][j], tileScreenX, tileScreenY, tileSize, tileSize, null);
            }
        }
//        while (worldCol < maxCol && worldRow < maxRow) {
//            // Calculate x and y position of tile
//            int worldX = worldCol * tileSize;
//            int worldY = worldRow * tileSize;
//            // Calculate x and y position of tile on screen
//
//            int screenX = worldX - gp.player.getWorldX() + playerScreenX;
//            int screenY = worldY - gp.player.getWorldY() + playerScreenY;
//
//            // Draw tile if it is on screen to save resources
//            if (worldX + tileSize > gp.player.getWorldX() - gp.player.getScreenX() && worldX - tileSize < gp.player.getWorldX() + gp.player.getScreenX()
//                    && worldY + tileSize > gp.player.getWorldY() - gp.player.getScreenY() && worldY - tileSize < gp.player.getWorldY() + gp.player.getScreenY()) {
//                g2d.drawImage(mapTileImage[worldCol][worldRow], screenX, screenY, tileSize, tileSize, null);
//            }
//            worldCol++;
//
//            if (worldCol == GameSettings.getMaxWorldCol()) {
//                worldCol = 0;
//                worldRow++;
//            }
//        }
    }

    public BufferedImage setup(String imgPath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imgPath + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
