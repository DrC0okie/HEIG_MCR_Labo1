package mcr.observerPattern;

import java.util.LinkedList;

/**
 * Abstract subject in the Observer design pattern. This class maintains a list of observers
 * and provides methods to attach, detach, and notify them of any state changes.
 * @author Samuel Roland, Timothée Van Hove
 */
public abstract class Subject {

    private final LinkedList<Observer> observers = new LinkedList<>();

    /**
     * Attaches an observer to this subject. The observer will be notified of any state changes.
     * @param observer The observer to attach.
     */
    public void attach(Observer observer) {
        observers.push(observer);
    }

    /**
     * Detaches an observer from this subject.
     * @param observer The observer to detach.
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers of a state change by calling their {@code update} method.
     */
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
}
