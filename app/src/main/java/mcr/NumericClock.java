package mcr;

import javax.swing.*;
import java.awt.*;

public class NumericClock extends ClockPanel {

    private final JLabel label = new JLabel();

    public NumericClock(Chrono chrono, Dimension dimension) {
        super(chrono, dimension);
        setLayout(new GridBagLayout());
        add(label);
        update();
    }

    @Override
    public void update() {
        label.setText("Chrono #" + chrono.getId() + ": " + chrono.getTime());
    }
}
