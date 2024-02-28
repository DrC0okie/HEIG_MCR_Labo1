package mcr;

import mcr.observerPattern.Subject;
import mcr.utils.SimpleTime;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.Closeable;

public class Chrono extends Subject implements Closeable {

    private SimpleTime currentTime;
    private final Timer timer;
    private static int instanceCount = 0;
    private final int id;
    private boolean isRunning;
    private final ActionListener listener  = e -> {
        currentTime.increment(1);
        notifyObservers();
    };

    public Chrono(){
        currentTime = new SimpleTime(0, 0, 0);
        isRunning = false;
        id = ++instanceCount;
        timer = new Timer(1000, listener);
    }

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

    public void toggle(){
        if (isRunning){
            stop();
        } else {
            start();
        }
    }

    public int getId(){
        return id;
    }

    public SimpleTime getTime() {
        return currentTime;
    }

    @Override
    public void close() {
        timer.stop();
        timer.removeActionListener(listener);
    }
}
