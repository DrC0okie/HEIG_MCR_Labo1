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

        // Resizable only if contains > 1 ClockPanel
        setResizable(panels.length > 1);

        // Set minimum size
        Dimension clockDimension = panels[0].getPreferredSize();
        setMinimumSize(new Dimension(clockDimension.width + 50, clockDimension.height + 50));

        // Pack to add responsiveness
        pack();
        setVisible(true);
    }

    public ClockFrame(ClockPanel panel) {
        this(new ClockPanel[]{panel});
    }
}
