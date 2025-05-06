package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuView class is responsible for creating and managing the application's menu bar and timer display.
 * It handles user interactions with menu items such as saving, loading, restarting the game,
 * accessing help, quitting the application, and also includes a timer functionality.
 *
 * This class acts as a View component in the context of an MVC (Model-View-Controller) architecture.
 * It delegates certain actions triggered by menu item selections to a controller via an ActionListener.
 * Coded by Lai Cal Wyn
 */
public class MenuView {
    private JMenuBar menuBar;
    private JLabel timerLabel;
    private Timer timer;
    private int elapsedTime; // in seconds

    /**
     * The MenuView class represents the main menu interface including file operations,
     * timer display, and other menu options such as help and quit.
     * This class initializes and manages the menu bar and its functionalities.
     * It interacts with a listener to handle user actions, such as saving or loading a game.
     *
     * This class can be integrated as part of the main UI to provide navigation and basic
     * functionalities needed during gameplay or application runtime.
     * Coded by Lai Cal Wyn
     */
    public MenuView(ActionListener listener) {
        menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Save option
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> {
            // Prompt the user for a file name
            String fileName = JOptionPane.showInputDialog(null, "Enter save file name:", "Save Game", JOptionPane.PLAIN_MESSAGE);
            if (fileName != null && !fileName.trim().isEmpty()) {
                // Pass the file name as part of the action command
                ActionEvent saveEvent = new ActionEvent(saveItem, ActionEvent.ACTION_PERFORMED, "Save:" + fileName.trim());
                listener.actionPerformed(saveEvent);
            } else {
                System.out.println("Save operation cancelled or invalid file name.");
            }
        });
        fileMenu.add(saveItem);

        // Load option
        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.setActionCommand("Load");
        loadItem.addActionListener(listener);
        fileMenu.add(loadItem);

        // Restart option
        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.setActionCommand("Restart");
        restartItem.addActionListener(listener);
        fileMenu.add(restartItem);

        // Help option
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setActionCommand("Help");
        helpItem.addActionListener(listener);
        fileMenu.add(helpItem);

        // Quit option
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setActionCommand("Quit");
        quitItem.addActionListener(listener);
        fileMenu.add(quitItem);

        // Timer label
        timerLabel = new JLabel("Time: 00:00:00");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(Box.createHorizontalGlue()); // Add glue to center the timer
        menuBar.add(timerLabel);
        menuBar.add(Box.createHorizontalGlue()); // Add glue to center the timer

        // Initialize timer
        elapsedTime = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateTimerLabel();
            }
        });
        timer.start(); // Start the timer
    }

    /**
     * Updates the display of the timer label in the format of hh:mm:ss based on the
     * elapsed time. This method calculates the hours, minutes, and seconds for the
     * current elapsed time and sets the formatted string to the timer label.
     *
     * This method is called periodically to ensure the timer label reflects the
     * up-to-date elapsed time during runtime.
     * Coded by Lai Cal Wyn
     */
    private void updateTimerLabel() {
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;

        // Format the time as hh:mm:ss
        String formattedTime = String.format("Time: %02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(formattedTime);
    }

    /**
     * Resets the timer to its initial state by setting the elapsed time to zero.
     *
     * This method ensures that the timer label is updated to reflect the reset
     * state, displaying a time of 00:00:00. It is used to restart the
     * timer as part of the application flow, such as when starting a new game or
     * resetting the application.
     * Coded by Lai Cal Wyn
     */
    //     Method to reset the timer
    public void resetTimer() {
        elapsedTime = 0; // Reset elapsed time
        updateTimerLabel(); // Update the label to show the reset time
    }

    /**
     * Retrieves the menu bar for the application interface.
     * The menu bar contains options such as file operations, help, and other menu-related functionalities.
     *
     * @return the JMenuBar associated with the menu view.
     * Coded by Lai Cal Wyn
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}