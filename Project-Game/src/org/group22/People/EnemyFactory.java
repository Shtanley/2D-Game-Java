package org.group22.People;

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
public class EnemyFactory {
    private GamePanel gp;

    public EnemyFactory(GamePanel gp) {
        this.gp = gp;
    }

    /**
     *
     *
     * @param filePath the location of the text file containing all items to be created
     */
    public void createEnemies(String filePath) {
        gp.enemies = new Enemy[gp.maxEnemies];
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load enemy file
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read enemy file

            // Skip header
            br.readLine();
            // Read lines
            int lineNum = 0;
            String line = br.readLine();
            while(line != null && lineNum < gp.maxItems) {
                String[] words = line.split(", "); // Read comma separated values
                String type = words[0];
                int x = Integer.parseInt(words[1]);
                int y = Integer.parseInt(words[2]);
                if(Objects.equals(type, "Bat")) {
                    Bat newBat = new Bat(gp);
                    newBat.setWorldX(x * gp.tileSize);
                    newBat.setWorldY(y * gp.tileSize);
                    gp.enemies[lineNum] = newBat;
                } else if(Objects.equals(type, "Skeleton")) {
                    Skeleton newSkeleton = new Skeleton(gp);
                    newSkeleton.setWorldX(x * gp.tileSize);
                    newSkeleton.setWorldY(y * gp.tileSize);
                    int i = 3;
                    while(i < words.length){
                        System.out.println(words[i]);
                        newSkeleton.addToPath(words[i]);
                        i++;
                    }
                    assert(newSkeleton.verifyPath());
                    gp.enemies[lineNum] = newSkeleton;
                }
                // Read next line
                line = br.readLine();
                lineNum++;
            }
            br.close();

        } catch (Exception ignored) {}
    }
}
