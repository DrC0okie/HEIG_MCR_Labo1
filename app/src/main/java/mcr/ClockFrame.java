package mcr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockFrame extends JFrame {

    public ClockFrame(ClockPanel[] panels) {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (ClockPanel clockPanel : panels)
                    clockPanel.detachFromChrono();
            }
        });

        for (ClockPanel clockPanel : panels)
            add(clockPanel);

        if (panels.length == 1)
            setResizable(false);

        setVisible(true);
        pack();
    }

    public ClockFrame(ClockPanel clockPanel) {
        this(new ClockPanel[]{clockPanel});
    }
}
