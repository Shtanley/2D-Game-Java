package main.java.app;

/**
 * Manages key game statistics
 *
 * @author Dina
 */
public class GameSettings {
    // Tile Settings
    public static final int orgTileSize = 16; // 16x16 pixels
    public static final int scale = 3;
    private static final int tileSize = orgTileSize * scale;

    // Screen Settings
    private static final int maxScreenCol = 21;
    private static final int maxScreenRow = 12;
    private static final int screenWidth = maxScreenCol * GameSettings.getTileSize();
    private static final int screenHeight = maxScreenRow * GameSettings.getTileSize();

    // World Settings
    private static final int maxWorldCol = 50;
    private static final int maxWorldRow = 50;

    // Entity settings
    private static final int maxItems = 50;
    private static final int maxTempItems = 100;

    // Level settings
    private static int keysNeeded;
    private static int healthDrainRate;
    private static int potionHealth = 50;
    private static int potionPoints = 50;
    private static int potionSpawnTimer = 100;
    private static double potionSpawnChance = 0.5;
    private static long potionLifetime = 5;


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

    public static int getMaxTempItems() {
        return maxTempItems;
    }

    public static int getMaxWorldCol() {
        return maxWorldCol;
    }

    public static int getMaxWorldRow() {
        return maxWorldRow;
    }

    public static int getKeysNeeded() {
        return keysNeeded;
    }

    public static void setKeysNeeded(int keysNeeded) {
        GameSettings.keysNeeded = keysNeeded;
    }

    public static int getHealthDrainRate() {
        return healthDrainRate;
    }

    public static void setHealthDrainRate(int healthDrainRate) {
        GameSettings.healthDrainRate = healthDrainRate;
    }

    public static int getPotionHealth() {
        return potionHealth;
    }

    public static void setPotionHealth(int potionHealth) {
        GameSettings.potionHealth = potionHealth;
    }

    public static int getPotionPoints() {
        return potionPoints;
    }

    public static void setPotionPoints(int potionPoints) {
        GameSettings.potionPoints = potionPoints;
    }

    public static int getPotionSpawnTimer() {
        return potionSpawnTimer;
    }

    public static void setPotionSpawnTimer(int potionSpawnTimer) {
        GameSettings.potionSpawnTimer = potionSpawnTimer;
    }

    public static double getPotionSpawnChance() {
        return potionSpawnChance;
    }

    public static void setPotionSpawnChance(double potionSpawnChance) {
        GameSettings.potionSpawnChance = potionSpawnChance;
    }

    public static long getPotionLifetime() {
        return potionLifetime;
    }

    public static void setPotionLifetime(long potionLifetime) {
        GameSettings.potionLifetime = potionLifetime;
    }
}
