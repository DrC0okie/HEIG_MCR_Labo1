package mcr.utils;

public class SimpleTime {

    private static final int HOUR_MAX = 24;
    private static final int MINUTE_MAX = 60;
    private static final int SECOND_MAX = 60;
    private static final int SECONDS_IN_HOUR = MINUTE_MAX * SECOND_MAX;

    public SimpleTime(int hours, int minutes, int seconds) {
        time = seconds + (minutes * SECOND_MAX) + (hours * SECONDS_IN_HOUR);
    }

    private int time;

    public int getHours() {
        return (time / SECONDS_IN_HOUR) % HOUR_MAX;
    }

    public int getMinutes() {
        return (time / MINUTE_MAX) % MINUTE_MAX;
    }

    public int getSeconds() {
        return time % SECOND_MAX;
    }

    public void increment(int seconds) {
        time += seconds;
    }

    public String toString(){
        return String.format("%02dh %02dm %02ds", getHours(), getMinutes(), getSeconds());
    }
}
