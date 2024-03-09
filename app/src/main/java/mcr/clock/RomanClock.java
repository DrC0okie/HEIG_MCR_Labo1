package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;

/**
 * Specialization class of {@link AnalogClock} that defines specific hand color and background image
 * with roman numbers
 *
 * @author Samuel Roland, Timothée Van Hove
 */
public class RomanClock extends AnalogClock {

    public RomanClock(Chrono chrono) {
        super(chrono, "img/cadran_chiffres_romains.jpg", Color.ORANGE, Color.GRAY, Color.BLACK);
    }
}
