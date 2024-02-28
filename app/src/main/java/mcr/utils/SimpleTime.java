package mcr.utils;

/**
 * Represents a simple time format with hours, minutes, and seconds.
 * @author Samuel Roland, Timothée Van Hove
 */
public class SimpleTime {

    private static final int HOUR_MAX = 24;
    private static final int MINUTE_MAX = 60;
    private static final int SECOND_MAX = 60;
    private static final int SECONDS_IN_HOUR = MINUTE_MAX * SECOND_MAX;

    /**
     * Constructs a SimpleTime instance with specified hours, minutes, and seconds.
     * @param hours The hour component of the time.
     * @param minutes The minute component of the time.
     * @param seconds The second component of the time.
     */
    public SimpleTime(int hours, int minutes, int seconds) {
        time = seconds + (minutes * SECOND_MAX) + (hours * SECONDS_IN_HOUR);
    }

    private int time;

    /**
     * Returns the hour component of the time.
     * @return The hour component.
     */
    public int getHours() {
        return (time / SECONDS_IN_HOUR) % HOUR_MAX;
    }

    /**
     * Returns the minute component of the time.
     * @return The minute component.
     */
    public int getMinutes() {
        return (time / MINUTE_MAX) % MINUTE_MAX;
    }

    /**
     * Returns the second component of the time.
     * @return The second component.
     */
    public int getSeconds() {
        return time % SECOND_MAX;
    }

    /**
     * Increments the time by a specified number of seconds.
     * @param seconds The number of seconds to add to the current time.
     */
    public void increment(int seconds) {
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
