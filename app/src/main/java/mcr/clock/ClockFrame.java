package mcr.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Specialized JFrame designed to display one or more {@link ClockPanel} instances.
 * It sets up the window properties, layout, and event listeners used to display the clocks
 * @author Samuel Roland, Timothée Van Hove
 */
public class ClockFrame extends JFrame {

    /**
     * Constructs a ClockFrame to display multiple ClockPanels.
     * @param panels An array of {@link ClockPanel} instances to be displayed within the frame.
     */
    public ClockFrame(ClockPanel[] panels) {
        setIconImage(new ImageIcon("img/icon.png").getImage());
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

    /**
     * Constructs a ClockFrame to display a single ClockPanel.
     * @param panel A single {@link ClockPanel} instance to be displayed within the frame.
     */
    public ClockFrame(ClockPanel panel) {
        this(new ClockPanel[]{panel});
    }
}
