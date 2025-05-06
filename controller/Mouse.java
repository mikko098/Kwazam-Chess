package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a custom Mouse class that extends MouseAdapter,
 * primarily focused on handling mouse click events. This class
 * works as an adapter to decouple the GUI-based mouse interaction
 * from the application's event-handling logic by delegating the
 * mouse click events to a designated handler.
 *
 * This class adheres to the Adapter pattern by providing a
 * concrete implementation that translates GUI events into
 * actionable event-handling logic via a MouseClickHandler.
 * This entire Mouse.java is coded by Mishal Mann Nair
 */
public class Mouse extends MouseAdapter {
    private MouseClickHandler clickHandler;

    // Interface to delegate mouse click handling to the Controller
    public interface MouseClickHandler {
        void onMouseClick(int x, int y);
    }

    /**
     * Represents a custom Mouse class that extends MouseAdapter,
     * allowing delegation of mouse click events to a provided handler.
     * The handler is intended to decouple mouse event handling logic
     * from the graphical user interface. This class is an adapter between
     * the MouseListener interface and the event-handling logic.
     *
     * @param clickHandler the handler responsible for responding to mouse click events.
     *                     Coded by Mishal Mann Nair
     */
    public Mouse(MouseClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    /**
     * Handles the mouse click event by retrieving the coordinates of the click
     * and delegating the handling of the event to the associated MouseClickHandler.
     *
     * @param e The MouseEvent containing information about the mouse click.
     *          Coded by Mishal Mann Nair
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (clickHandler != null) {
            clickHandler.onMouseClick(x, y); // Delegate event to the Controller
        }
    }
}