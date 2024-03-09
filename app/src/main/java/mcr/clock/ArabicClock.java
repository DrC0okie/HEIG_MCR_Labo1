package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;

/**
 * Specialization class of {@link AnalogClock} that defines specific hand color and background image
 * with arabic numbers
 *
 * @author Samuel Roland, Timothée Van Hove
 */
public class ArabicClock extends AnalogClock {

    public ArabicClock(Chrono chrono) {
        super(chrono, "img/cadran_chiffres_arabes.jpg", Color.RED, Color.BLUE, Color.BLACK);
    }
}
