package mcr.clock;

import mcr.chrono.Chrono;
import mcr.observer.Observer;
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

    protected static final Dimension CLOCK_DIMENSION = new Dimension(200, 200);
    protected final Chrono chrono;

    /**
     * Constructs a new ClockPanel associated with a given {@link Chrono} and sets its size.
     * @param chrono The {@link Chrono} instance to associate with this panel.
     */
    protected ClockPanel(Chrono chrono) {
        this.chrono = chrono;
        this.chrono.attach(this);
        setPreferredSize(CLOCK_DIMENSION);
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
        if (chrono != null)
            chrono.detach(this);
    }
}
