package mcr;

import mcr.observerPattern.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ClockPanel extends JPanel implements Observer {

    protected final Chrono chrono;

    protected ClockPanel(Chrono chrono, Dimension dimension) {
        this.chrono = chrono;
        this.chrono.attach(this);

        setPreferredSize(dimension);
        boolean clicked = false;
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
