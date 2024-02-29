package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;


public class ArabicClock extends AnalogClock {

    private static final String IMAGE_PATH = "img/cadran_chiffres_arabes.jpg";

    public ArabicClock(Chrono chrono) {
        super(chrono, IMAGE_PATH);
        setSecondHandColor(Color.RED);
        setMinuteHandColor(Color.BLUE);
        setHourHandColor(Color.BLACK);
    }
}

