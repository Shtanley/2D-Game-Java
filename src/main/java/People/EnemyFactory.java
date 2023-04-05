package People;

import app.GamePanel;
import app.GameSettings;

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
    private final GamePanel gp;
    private static final int maxEnemies = 50;
    private final int tileSize = GameSettings.getTileSize();

    public EnemyFactory(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Create all enemies in the game as specified by a csv text file
     *
     * @param filePath the location of the text file containing all items to be created
     */
    public void createEnemies(String filePath) {
        gp.enemies = new Enemy[maxEnemies];
        try {
            InputStream is = getClass().getResourceAsStream(filePath);    // Load enemy file
            if(is == null) {
                throw new RuntimeException();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read enemy file

            // Skip header
            br.readLine();
            // Read lines
            int lineNum = 0;
            String line = br.readLine();
            while(line != null && lineNum < GameSettings.getMaxItems()) {
                String[] words = line.split(", "); // Read comma separated values
                String type = words[0];
                int x = Integer.parseInt(words[1]);
                int y = Integer.parseInt(words[2]);
                if(Objects.equals(type, "Bat")) {
                    Bat newBat = new Bat(gp);
                    newBat.setWorldX(x * tileSize);
                    newBat.setWorldY(y * tileSize);
                    gp.enemies[lineNum] = newBat;
                } else if(Objects.equals(type, "Skeleton")) {
                    Skeleton newSkeleton = new Skeleton(gp);
                    newSkeleton.setWorldX(x * tileSize);
                    newSkeleton.setWorldY(y * tileSize);
                    int i = 3;
                    while(i < words.length){
                        newSkeleton.addToPath(words[i]);
                        i++;
                    }
                    assert(newSkeleton.verifyPath());
                    gp.enemies[lineNum] = newSkeleton;
                } else if (Objects.equals(type, "Slime")){
                    Slime newSlime = new Slime(gp);
                    newSlime.setWorldX(x * tileSize);
                    newSlime.setWorldY(y * tileSize);
                    gp.enemies[lineNum] = newSlime;
                } else {
                    // Invalid enemy
                    System.out.println("Invalid enemy type: \"" + type + "\" on line " + lineNum + " of " + filePath);
                }
                // Read next line
                line = br.readLine();
                lineNum++;
            }
            br.close();

        } catch (Exception ignored) {
            System.out.println("Failed to load enemies from " + filePath);
        }
    }
}
