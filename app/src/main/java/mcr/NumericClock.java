package mcr;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the current time of a {@link Chrono} instance in a numeric format.
 * It updates its display in response to changes in the associated Chrono.
 * @author Samuel Roland, Timothée Van Hove
 */
public class NumericClock extends ClockPanel {

    private final JLabel label = new JLabel();


    /**
     * Constructs a NumericClock associated with a specific {@link Chrono} and sets its size.
     * @param chrono The Chrono instance whose time this clock displays.
     * @param dimension The preferred dimension for this clock display.
     */
    public NumericClock(Chrono chrono, Dimension dimension) {
        super(chrono, dimension);
        setLayout(new GridBagLayout());
        add(label);
        update();
    }

    /**
     * Updates the label of the NumericClock to show the updated time of the {@link Chrono}.
     * This method is called whenever the Chrono notifies its observers of a change.
     */
    @Override
    public void update() {
        label.setText("Chrono #" + chrono.getId() + ": " + chrono.getTime());
    }
}
