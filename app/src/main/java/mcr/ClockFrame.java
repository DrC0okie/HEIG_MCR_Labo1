package mcr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClockFrame {

    public ClockFrame(ClockPanel[] panels) {

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (ClockPanel clockPanel : panels)
                   clockPanel.detachFromChrono();
            }
        });

        for (ClockPanel clockPanel : panels)
            frame.add(clockPanel);

        if(panels.length == 1)
            frame.setResizable(false);

        frame.setVisible(true);
        frame.pack();
    }

    public ClockFrame(ClockPanel clockPanel) {
        this(new ClockPanel[]{clockPanel});
    }
}
