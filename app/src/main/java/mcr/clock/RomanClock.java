package mcr.clock;

import mcr.chrono.Chrono;
import java.awt.*;

public class RomanClock extends AnalogClock{

    private static final String IMAGE_PATH = "img/cadran_chiffres_romains.jpg";

    public RomanClock(Chrono chrono){
        super(chrono, IMAGE_PATH);
        setSecondHandColor(Color.ORANGE);
        setMinuteHandColor(Color.GRAY);
        setHourHandColor(Color.BLACK);
    }
}
