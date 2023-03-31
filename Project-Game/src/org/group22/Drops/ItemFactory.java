package org.group22.Drops;

import org.group22.app.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

/**
 * ItemFactory class
 * Create items as specified by a given csv-style items text file
 *
 * @author Sameer
 * @author Michael
 */
public class ItemFactory {
    private final GamePanel gp;

    public ItemFactory(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Fills the game panel's array of objects based on a csv text file containing data: (type, xCoordinate, yCoordinate)
     * There is a different text file for each level
     *
     * @param filePath the location of the text file containing all items to be created
     */
    public void createItems(String filePath) {
        gp.obj = new Item[gp.maxItems];
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load map file
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read map file

            // Skip header
            br.readLine();
            // Read lines
            int lineNum = 0;
            String line = br.readLine();
            while(line != null && lineNum < gp.maxItems) {
                String[] words = line.split(", "); // Read "type, x, y" comma separated values
                String type = words[0];
                int x = Integer.parseInt(words[1]) * gp.tileSize;
                int y = Integer.parseInt(words[2]) * gp.tileSize;
                if(Objects.equals(type, "Key")) {
                    gp.obj[lineNum] = new Key(x, y);
                } else if(Objects.equals(type, "Door")) {
                    gp.obj[lineNum] = new Door(x, y);
                } else if(Objects.equals(type, "Spikes")) {
                    gp.obj[lineNum] = new Spikes(x, y);
                }
                // Read next line
                line = br.readLine();
                lineNum++;
            }
            br.close();

        } catch (Exception ignored) {}
    }

    /**
     * Spawns a potion at a valid random location within 10 squares of the player in either direction
     * A location is valid if its collision is off, and it does not already contain an item
     *
     * @return the potion created
     */
    public Potion spawnPotion(){
        Random rand = new Random();
        int x, y;
        boolean validLoc;
        do {
            validLoc = true;
            // Attempt to spawn potion at random location within 10 tiles of the player in each direction
            int xComponent = rand.nextInt(21) - 10; // Random int between -10 and 10
            int yComponent = rand.nextInt(21) - 10; // Random int between -10 and 10
            // Calculate (x, y) on map
            x = gp.player.getWorldX()/gp.tileSize + xComponent;
            y = gp.player.getScreenY()/gp.tileSize + yComponent;
            // Check if valid location:
            // Omit (0, 0). Potion should not spawn on top of the player
            if(xComponent == 0 && yComponent == 0) {
                validLoc = false;
                continue;
            }
            // Location must be on the map
            if(x < 1 || x >= gp.cFactory.mapWidth-1 || y < 1 || y >= gp.cFactory.mapHeight-1) {
                validLoc = false;
                continue;
            }
            // Location must have collision off
            if (gp.cFactory.mc[gp.cFactory.mapTileNum[x][y]].isCollisionOn()) {
                validLoc = false;
                continue;
            }
            // Location cannot already have an item
            for (Item item : gp.obj) {
                if (item != null && item.getWorldX()/gp.tileSize == x && item.getWorldY()/gp.tileSize == y) {
                    validLoc = false;
                    break;
                }
            }
            for (BonusReward bonus : gp.tempItems) {
                if (bonus.getWorldX()/gp.tileSize == x && bonus.getWorldY()/gp.tileSize == y) {
                    validLoc = false;
                    break;
                }
            }
        } while(!validLoc);
        System.out.println("Potion spawned");
        return new Potion(x*gp.tileSize, y*gp.tileSize, gp.timer);
    }

}
