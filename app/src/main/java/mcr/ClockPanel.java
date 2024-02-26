package mcr;

import mcr.observerPattern.Observer;
import javax.swing.*;
import java.awt.*;

public abstract class ClockPanel extends JPanel implements Observer {

    protected final Chrono chrono;
    protected final Dimension dimension;

    protected ClockPanel(Chrono chrono, Dimension dimension) {
        this.chrono = chrono;
        this.chrono.attach(this);

        this.dimension = dimension;
        setPreferredSize(dimension);

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //TODO: Toggle run/stop
//            }
//        });
    }

    public void detachFromChrono() {
        chrono.detach(this);
    }
}
