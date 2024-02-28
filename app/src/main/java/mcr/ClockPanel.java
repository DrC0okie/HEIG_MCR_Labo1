package mcr;

import mcr.observerPattern.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Abstract representation of a clock display panel, this class implements the {@link Observer}
 * interface, allowing it to receive updates from a {@link Chrono} instance.
 * @author Samuel Roland, Timothée Van Hove
 */
public abstract class ClockPanel extends JPanel implements Observer {

    protected final Chrono chrono;

    /**
     * Constructs a new ClockPanel associated with a given {@link Chrono} and sets its size.
     * @param chrono The {@link Chrono} instance to associate with this panel.
     * @param dimension The preferred size of the panel.
     */
    protected ClockPanel(Chrono chrono, Dimension dimension) {
        this.chrono = chrono;
        this.chrono.attach(this);
        setPreferredSize(dimension);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chrono.toggle();
            }
        });
    }

    /**
     * Detaches this panel from its associated {@link Chrono}, stopping it from receiving updates.
     */
    public void detachFromChrono() {
        chrono.detach(this);
    }
}
