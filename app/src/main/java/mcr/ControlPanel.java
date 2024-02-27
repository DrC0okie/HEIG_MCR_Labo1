package mcr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.function.Function;

public class ControlPanel {

    private static final Dimension CLOCK_DIMENSION = new Dimension(200, 200);
    private final Function<Chrono, ClockPanel> romanClockFactory = chrono -> new AnalogClock(chrono,
            CLOCK_DIMENSION, AnalogClockType.ROMAN);
    private final Function<Chrono, ClockPanel> arabicClockFactory = chrono -> new AnalogClock(chrono,
            CLOCK_DIMENSION, AnalogClockType.ARABIC);
    private final Function<Chrono, ClockPanel> numericClockFactory = chrono -> new NumericClock(chrono,
            CLOCK_DIMENSION);


    ControlPanel(int nbClock) {
        JFrame frame = new JFrame();
        frame.setIconImage(new ImageIcon("img/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Panneau de contrôle");
        frame.setResizable(false);

        LinkedList<Chrono> chronos = new LinkedList<>();

        frame.setLayout(new GridLayout(nbClock + 1, 1));

        for (int i = 0; i < nbClock; i++) {
            chronos.add(new Chrono());

            JPanel clockPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            frame.add(clockPanel);

            clockPanel.add(new JLabel("Chrono #" + (i + 1)));

            addSingleClockButtons(clockPanel, chronos.get(i));
        }

        addMultiClockButtons(frame, chronos, nbClock);

        // Enable the frame size to fit correctly its content
        frame.pack();
        frame.setVisible(true);
    }

    private void addButton(String name, JPanel toPanel, ActionListener actionListener) {
        JButton button = new JButton(name);
        toPanel.add(button);
        button.addActionListener(actionListener);
    }

    private void addSingleClockButtons(JPanel clockPanel, Chrono chrono) {
        addButton("Démarrer", clockPanel, e -> chrono.start());
        addButton("Arrêter", clockPanel, e -> chrono.stop());
        addButton("Réinitialiser", clockPanel, e -> chrono.reset());
        addButton("Numérique", clockPanel, e -> showClock(chrono, numericClockFactory));
        addButton("Cadran romain", clockPanel, e -> showClock(chrono, romanClockFactory));
        addButton("Cadran arabe", clockPanel, e -> showClock(chrono, arabicClockFactory));
    }

    private void addMultiClockButtons(JFrame frame, LinkedList<Chrono> chronos, int nbClock) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new JLabel("Tous les chronos"));

        addButton("Cadrans romains", panel, e -> showClocks(chronos, romanClockFactory, nbClock));
        addButton("Cadrans arabes", panel, e -> showClocks(chronos, arabicClockFactory, nbClock));
        addButton("Numériques", panel, e -> showClocks(chronos, numericClockFactory, nbClock));

        frame.add(panel);
    }

    // Helper method to create and show multiple ClockFrame objects
    private void showClocks(LinkedList<Chrono> chronos, Function<Chrono, ClockPanel> factory, int nbClock) {
        ClockPanel[] panels = chronos.stream().limit(nbClock).map(factory).toArray(ClockPanel[]::new);
        new ClockFrame(panels);
    }

    private void showClock(Chrono chrono, Function<Chrono, ClockPanel> factory) {
        new ClockFrame(factory.apply(chrono));
    }
}
