package mcr.chrono;

import mcr.observer.Subject;
import javax.swing.*;

/**
 * Simple chronometer that can start, stop, and reset. Notifies its observers when the time changes.
 * @author Samuel Roland, Timothée Van Hove
 */
public class Chrono extends Subject {

    private SimpleTime currentTime;
    private final Timer timer;
    private static int instanceCount = 0;
    private final int id;

    /**
     * Constructs a new Chrono instance with the time set to 00:00:00.
     */
    public Chrono() {
        currentTime = new SimpleTime(0);
        id = ++instanceCount;
        // Setup a timer with a 1 second (1000ms) interval
        timer = new Timer(1000, e -> {
            currentTime.increment(1);
            notifyObservers();
        });
    }

    /**
     * Starts the chronometer.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the chronometer.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Resets the chronometer time to 00:00:00.
     */
    public void reset() {
        currentTime = new SimpleTime(0);
        notifyObservers();
    }

    /**
     * Toggles the chronometer state between running and stopped.
     */
    public void toggle() {
        if (timer.isRunning()) {
            stop();
        } else {
            start();
        }
    }

    /**
     * Returns the textual identifier of the Chrono
     * @return the text id
     */
    @Override
    public String toString() {
        return "Chrono #" + id;
    }

    /**
     * Returns the current time of the chronometer.
     * @return The current time as a {@link SimpleTime} instance.
     */
    public SimpleTime getTime() {
        return currentTime;
    }
}
