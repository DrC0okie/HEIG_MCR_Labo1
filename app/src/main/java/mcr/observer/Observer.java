package mcr.observer;

/**
 * Observer interface of the Observer design pattern. Classes that implement this interface
 * can subscribe to notifications from {@link Subject} instances they observe.
 * @author Samuel Roland, Timothée Van Hove
 */
public interface Observer {

    /**
     * Method called by the {@link Subject} when a state changes.
     */
    void update();
}
