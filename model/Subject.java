package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a subject in the Observer design pattern.
 * A subject maintains a list of observers and provides mechanisms
 * to add, remove, and notify those observers about state changes.
 *
 * This class is the central part of the Observer design pattern,
 * responsible for managing its relationship with observers and ensuring
 * they are notified whenever the subject's state changes.
 * Coded by Lai Cal Wyn
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers for this subject.
     * An observer is an object that implements the Observer interface and will be notified
     * of changes or updates to the state of this subject.
     *
     * @param observer The observer to be added to the list of observers.
     *                 Coded by Lai Cal Wyn
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of registered observers for this subject.
     *
     * @param observer The observer to be removed from the list of observers.
     *                 This observer will no longer receive notifications from the subject.
     *                 Coded by Lai Cal Wyn
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

