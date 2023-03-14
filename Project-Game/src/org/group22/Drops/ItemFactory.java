package org.group22.Drops;

import org.group22.app.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * ItemFactory class
 * Create items
 * @author Sameer
 */
public class ItemFactory {
    GamePanel gp;
    ArrayList<Item> obj;

    public ItemFactory(GamePanel gp) {
        this.gp = gp;
        this.obj = gp.obj;
        System.out.println(obj);
    }

    public ArrayList<Item> createItems(String filePath) {
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
                    if(num == 7) {
                        // Keys
                        obj.add(new Key());
                        obj.get(obj.size()-1).worldX = col * gp.tileSize;
                        obj.get(obj.size()-1).worldY = row * gp.tileSize;
                    }
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
        return obj;
    }
}
