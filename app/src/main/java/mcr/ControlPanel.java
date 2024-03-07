package mcr;

import mcr.chrono.Chrono;
import mcr.clock.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

        // Set up the parameters of the main frame of the application
        frame = new JFrame();
        frame.setIconImage(new ImageIcon("img/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Panneau de contrôle");
        frame.setResizable(false);
        frame.setLayout(new GridLayout(nbClock + 1, 1));

        for (int i = 0; i < nbClock; i++) {
            // Add the desired number of chronos to the list
            var chrono = new Chrono();
            chronos.add(chrono);

            // For each chrono, create a new panel, add the label and the buttons to it
            JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            clockPanel.add(new JLabel(chrono.toString()));
            addSingleClockButtons(clockPanel, chronos.get(i));

            // Add the panel to the main frame
            frame.add(clockPanel);
        }

        addMultiClockPanel();

        // Enable the frame to fit correctly its content
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds buttons for a single clock to the specified panel.
     *
     * @param commandPanel The panel to which the control buttons will be added.
     * @param chrono     The {@link Chrono} instance associated with the clock being controlled.
     */
    private void addSingleClockButtons(JPanel commandPanel, Chrono chrono) {
        addButton("Démarrer", commandPanel, e -> chrono.start());
        addButton("Arrêter", commandPanel, e -> chrono.stop());
        addButton("Réinitialiser", commandPanel, e -> chrono.reset());
        addButton("Cadran romain", commandPanel, e -> showClock(chrono, RomanClock::new));
        addButton("Cadran arabe", commandPanel, e -> showClock(chrono, ArabicClock::new));
        addButton("Numérique", commandPanel, e -> showClock(chrono, NumericClock::new));
    }

    /**
     * Adds the panel that holds the buttons used to display all clocks simultaneously.
     */
    private void addMultiClockPanel() {
        // Create the panel and add a label to it
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new JLabel("Tous les chronos"));

        addButton("Cadran romain", panel, e -> showClocks(RomanClock::new));
        addButton("Cadran arabe", panel, e -> showClocks(ArabicClock::new));
        addButton("Numérique", panel, e -> showClocks(NumericClock::new));

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
