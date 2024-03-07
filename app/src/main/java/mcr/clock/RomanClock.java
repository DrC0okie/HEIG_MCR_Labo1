package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;

public class RomanClock extends AnalogClock {

    public RomanClock(Chrono chrono) {
        super(chrono, "img/cadran_chiffres_romains.jpg", Color.ORANGE, Color.GRAY, Color.BLACK);
    }
}
