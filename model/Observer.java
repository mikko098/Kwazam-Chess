package model;

/**
 * The Observer interface is a core part of the Observer design pattern.
 * It defines a contract for objects that wish to be notified of updates
 * or changes in the state of a subject they are observing.
 *
 * Implementing classes need to provide behavior for the `update` method,
 * which is called when the observed subject notifies its observers.
 * Coded by Mishal Mann Nair
 */
public interface Observer {
    void update(); // Called when the model.Subject notifies observers
}