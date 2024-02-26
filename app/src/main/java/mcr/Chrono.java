package mcr;

import mcr.observerPattern.Subject;
import mcr.utils.SimpleTime;
import javax.swing.*;

public class Chrono extends Subject {

    public Chrono(){
        currentTime = new SimpleTime(0, 0, 0);
        isRunning = false;
        id = ++instanceCount;
        timer = new Timer(1000, e -> {
            currentTime.increment(1);
            notifyObservers();
        });
    }

    private SimpleTime currentTime;
    private final Timer timer;
    private static int instanceCount = 0;
    private final int id;
    private boolean isRunning;

    public void start(){
        timer.start();
        isRunning = true;
    }

    public void stop(){
        timer.stop();
        isRunning = false;
    }

    public void reset(){
        currentTime = new SimpleTime(0, 0, 0);
        notifyObservers();
    }

    public int getId(){
        return id;
    }

    public SimpleTime getTime() {
        return currentTime;
    }
}
