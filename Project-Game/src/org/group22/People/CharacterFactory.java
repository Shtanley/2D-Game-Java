package org.group22.People;

import org.group22.Drops.Door;
import org.group22.Drops.Key;
import org.group22.Drops.Potion;
import org.group22.Drops.Spikes;
import org.group22.app.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * CharacterFactory class
 * Handles creation of non-player characters
 *
 * @author Michael
 */
public class CharacterFactory {
    private GamePanel gp;

    public CharacterFactory(GamePanel gp) {
        this.gp = gp;
    }

    /**
     *
     *
     * @param filePath the location of the text file containing all items to be created
     */
    public void createItem(String filePath) {
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
                int x = Integer.parseInt(words[1]);
                int y = Integer.parseInt(words[2]);
                if(Objects.equals(type, "Key")) {
                    gp.obj[lineNum] = new Key();
                    gp.obj[lineNum].worldX = x * gp.tileSize;
                    gp.obj[lineNum].worldY = y * gp.tileSize;
                } else if(Objects.equals(type, "Potion")) {
                    gp.obj[lineNum] = new Potion();
                    gp.obj[lineNum].worldX = x * gp.tileSize;
                    gp.obj[lineNum].worldY = y * gp.tileSize;
                } else if(Objects.equals(type, "Door")) {
                    gp.obj[lineNum] = new Door();
                    gp.obj[lineNum].worldX = x * gp.tileSize;
                    gp.obj[lineNum].worldY = y * gp.tileSize;
                } else if(Objects.equals(type, "Spikes")) {
                    gp.obj[lineNum] = new Spikes();
                    gp.obj[lineNum].worldX = x * gp.tileSize;
                    gp.obj[lineNum].worldY = y * gp.tileSize;
                } else if(Objects.equals(type, "Bat")) {    // Need to repostion bats in the next map
                    gp.bat[lineNum] = new Bat(gp);
                    gp.bat[lineNum].worldX = x * gp.tileSize;
                    gp.bat[lineNum].worldY = y * gp.tileSize;
                }
                // Read next line
                line = br.readLine();
                lineNum++;
            }
            br.close();

        } catch (Exception ignored) {}
    }
}
