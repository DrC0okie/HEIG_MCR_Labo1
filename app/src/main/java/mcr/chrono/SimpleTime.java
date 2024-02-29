package mcr.chrono;

/**
 * Represents a simple time format with hours, minutes, and seconds.
 * @author Samuel Roland, Timothée Van Hove
 */
public class SimpleTime {

    private static final int HOUR_CYCLE = 24;
    private static final int MINUTE_CYCLE = 60;
    private static final int SECOND_CYCLE = 60;
    private static final int SECONDS_IN_HOUR = MINUTE_CYCLE * SECOND_CYCLE;
    private int time;

    /**
     * Constructs a SimpleTime instance with specified seconds.
     * @param time The time represented in seconds
     */
    public SimpleTime(int time) {
        this.time = time;
    }

    /**
     * Returns the hour component of the time.
     * @return The hour component.
     */
    public int getHours() {
        return (time / SECONDS_IN_HOUR) % HOUR_CYCLE;
    }

    /**
     * Returns the minute component of the time.
     * @return The minute component.
     */
    public int getMinutes() {
        return (time / MINUTE_CYCLE) % MINUTE_CYCLE;
    }

    /**
     * Returns the second component of the time.
     * @return The second component.
     */
    public int getSeconds() {
        return time % SECOND_CYCLE;
    }

    /**
     * Increments the time by a specified number of seconds.
     * @param seconds The number of seconds to add to the current time.
     */
    void increment(int seconds) {
        time += seconds;
    }

    /**
     * Returns a string representation of the time in the format "HHh MMm SSs".
     * @return The formatted time string.
     */
    public String toString(){
        return String.format("%02dh %02dm %02ds", getHours(), getMinutes(), getSeconds());
    }
}
