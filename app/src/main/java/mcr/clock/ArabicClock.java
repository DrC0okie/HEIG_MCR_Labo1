package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;

public class ArabicClock extends AnalogClock {

    public ArabicClock(Chrono chrono) {
        super(chrono, "img/cadran_chiffres_arabes.jpg");
        setSecondHandColor(Color.RED);
        setMinuteHandColor(Color.BLUE);
        setHourHandColor(Color.BLACK);
    }
}
