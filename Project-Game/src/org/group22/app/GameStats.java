package org.group22.app;

/**
 * Manages key game statistics
 *
 * @author Dina
 */
public class GameStats {
    public static final int orgTileSize = 16; // 16x16 pixels
    public static final int scale = 3;
    private static final int tileSize = orgTileSize * scale;

    // Screen Settings
    private static final int maxScreenCol = 21;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = maxScreenCol * GameStats.getTileSize();
    private static final int screenHeight = maxScreenRow * GameStats.getTileSize();

    private static final int maxWorldCol = 50;
    private static final int maxWorldRow = 50;


    // Entity settings
    private static final int maxItems = 50;


    public static int getTileSize(){
        return tileSize;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getMaxItems(){
        return maxItems;
    }

    public static int getMaxWorldCol() {
        return maxWorldCol;
    }

    public static int getMaxWorldRow() {
        return maxWorldRow;
    }
}
