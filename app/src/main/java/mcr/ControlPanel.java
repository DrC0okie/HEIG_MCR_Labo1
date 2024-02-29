package mcr;

import mcr.chrono.Chrono;
import mcr.clock.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * Displays a control GUI for managing multiple clocks. Controls a specified number of clocks
 * represented by a {@link Chrono} and displayed by a {@link ClockPanel}.
 *
 * @author Samuel Roland, Timothée Van Hove
 */
public class ControlPanel {

    private final int nbClock;
    private final LinkedList<Chrono> chronos;
    private final JFrame frame;

    /**
     * Constructs the ControlPanel with a specified number of clocks.
     *
     * @param nbClock The number of clocks to be managed.
     */
    ControlPanel(int nbClock) {
        this.nbClock = nbClock;
        chronos = new LinkedList<>();

        frame = new JFrame();
        frame.setIconImage(new ImageIcon("img/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Panneau de contrôle");
        frame.setResizable(false);
        frame.setLayout(new GridLayout(nbClock + 1, 1));

        for (int i = 0; i < nbClock; i++) {
            // Add the desired number of chronos to the list
            chronos.add(new Chrono());

            // For each chrono, create a new panel, add the label and the buttons to it
            JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            clockPanel.add(new JLabel("Chrono #" + (i + 1)));
            addSingleClockButtons(clockPanel, chronos.get(i));

            // Add the panel to the main frame
            frame.add(clockPanel);
        }

        addMultiClockPanel();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Close the chronos before exit
                for (Chrono chrono : chronos)
                    chrono.close();
            }
        });

        // Enable the frame size to fit correctly its content
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds buttons for a single clock to the specified panel.
     *
     * @param clockPanel The panel to which the control buttons will be added.
     * @param chrono     The {@link Chrono} instance associated with the clock being controlled.
     */
    private void addSingleClockButtons(JPanel clockPanel, Chrono chrono) {
        addButton("Démarrer", clockPanel, e -> chrono.start());
        addButton("Arrêter", clockPanel, e -> chrono.stop());
        addButton("Réinitialiser", clockPanel, e -> chrono.reset());
        addButton("Numérique", clockPanel, e -> showClock(chrono, NumericClock::new));
        addButton("Cadran romain", clockPanel, e -> showClock(chrono, RomanClock::new));
        addButton("Cadran arabe", clockPanel, e -> showClock(chrono, ArabicClock::new));
    }

    /**
     * Adds the panel that holds the buttons used to display all clocks simultaneously.
     */
    private void addMultiClockPanel() {
        // Create the panel and add a label to it
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new JLabel("Tous les chronos"));

        addButton("Cadrans romains", panel, e -> showClocks(RomanClock::new));
        addButton("Cadrans arabes", panel, e -> showClocks(ArabicClock::new));
        addButton("Numériques", panel, e -> showClocks(NumericClock::new));

        frame.add(panel);
    }

    /**
     * Adds a button to a given panel and attaches the provided action listener to it.
     *
     * @param name           The text to be displayed on the button.
     * @param panel          The panel to which the button will be added.
     * @param actionListener The action listener to be attached to the button.
     */
    private void addButton(String name, JPanel panel, ActionListener actionListener) {
        JButton button = new JButton(name);
        panel.add(button);
        button.addActionListener(actionListener);
    }

    /**
     * Displays multiple {@link ClockPanel} in the same {@link ClockFrame}, using the specified factory
     * to create the {@link ClockPanel} for each {@link Chrono}.
     *
     * @param factory The factory to create {@link ClockPanel} instances for each {@link Chrono}.
     */
    private void showClocks(Function<Chrono, ClockPanel> factory) {

        // For each chrono
        ClockPanel[] panels = chronos.stream().limit(nbClock).map(factory).toArray(ClockPanel[]::new);
        new ClockFrame(panels);
    }

    /**
     * Displays a single clock in a {@link ClockFrame}, using the specified factory to create
     * the {@link ClockPanel} for the given {@link Chrono}.
     *
     * @param chrono  The {@link Chrono} instance to be displayed.
     * @param factory The factory to create a {@link ClockPanel} instance for the {@link Chrono}.
     */
    private void showClock(Chrono chrono, Function<Chrono, ClockPanel> factory) {
        new ClockFrame(factory.apply(chrono));
    }
}
