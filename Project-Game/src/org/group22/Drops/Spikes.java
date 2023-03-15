package org.group22.Drops;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Spikes extends Punishment {
    public Spikes() {
        name = "Spikes";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/tent.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
