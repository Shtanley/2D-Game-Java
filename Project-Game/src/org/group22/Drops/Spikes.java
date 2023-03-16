package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Create spike punishment item
 *
 * @author Michael
 */
public class Spikes extends Punishment {
    public Spikes() {
        name = "Spikes";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/peaks_1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
