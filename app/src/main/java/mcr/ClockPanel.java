package mcr;

import mcr.observerPattern.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ClockPanel extends JPanel implements Observer {

    protected final Chrono chrono;

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

    public void detachFromChrono() {
        chrono.detach(this);
    }
}
