package mcr.observerPattern;

import java.util.LinkedList;

public abstract class Subject {

    private final LinkedList<Observer> observers = new LinkedList<>();

    public void attach(Observer observer) {
        observers.push(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
