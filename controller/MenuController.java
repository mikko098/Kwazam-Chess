package controller;

import view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuController class acts as a controller in the MVC (Model-View-Controller) design pattern.
 * It handles menu-related actions such as saving, loading, restarting, displaying help, and quitting the game.
 * This class listens for action events and triggers appropriate methods in the associated GameController.
 * Coded by Lai Cal Wyn
 */
public class MenuController implements ActionListener {
    private GameController gameController;


    /**
     * The MenuController class is responsible for handling menu-related actions
     * and events within the application. It acts as a controller in the
     * Model-View-Controller (MVC) design pattern, connecting user menu interactions
     * with the underlying application logic.
     *
     * This class manages various menu operations such as save, load, restart, help,
     * and quit. It delegates these operations to specific methods and interacts
     * with the associated GameController instance to perform game-related logic.
     * Coded by Lai Cal Wyn
     */
    public MenuController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.startsWith("Save:")) {
            String fileName = command.substring(5); // Extract the file name from the action command
            handleSave(fileName);
        } else if (command.equals("Load")) {
            handleLoad();
        } else if (command.equals("Restart")) {
            handleRestart();
        } else if (command.equals("Help")) {
            handleHelp();
        } else if (command.equals("Quit")) {
            handleQuit();
        }
    }

    /**
     * Handles the save operation for the application. This method
     * delegates the process of saving the game's state to the associated
     * GameController instance, ensuring that the current state is persistently
     * stored with the provided file name.
     *
     * @param fileName The name of the file where the game state will be saved.
     *                 Coded by Lai Cal Wyn
     */
    private void handleSave(String fileName) {
        System.out.println("Saving the game with file name: " + fileName);
        gameController.saveGame(fileName);
    }

    /**
     * Handles the logic for loading a game file into the application's state.
     * This method displays a file chooser dialog to the user, allowing them
     * to select a game save file. If the user approves the selection, the file
     * path is retrieved, and the game loading process is delegated to the
     * associated GameController instance.
     *
     * If the user cancels the file selection, a corresponding message is logged
     * to indicate the cancellation of the load operation.
     *
     * This method serves as part of the menu-related actions in the MenuController
     * class and ensures user-driven file-based game state management.
     * Coded by Lai Cal Wyn
     */
    private void handleLoad() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getPath();
            System.out.println("Loading game from: " + fileName);
            gameController.loadGame(fileName);
        } else {
            System.out.println("Load operation cancelled by the user.");
        }
    }

    /**
     * Handles the logic for restarting the game by delegating to the associated
     * GameController instance. This method ensures that the game state is reset,
     * the game timer is prepared for a new game, and all necessary observers
     * are notified of the restart.
     * Coded by Lai Cal Wyn
     */
    private void handleRestart() {
        System.out.println("Restarting the game...");
        gameController.restartGame();
    }

    /**
     * The MenuController class is responsible for handling menu-related
     * actions and events within the application. It acts as a controller
     * in the Model-View-Controller (MVC) pattern, connecting user menu
     * interactions with the underlying application logic.
     *
     * This class manages various menu operations like save, load, restart,
     * help, and quit commands, delegating functionality to the respective
     * methods and interacting with the GameController when required.
     * Coded by Lai Cal Wyn
     */
    private void handleHelp() {
        JOptionPane.showMessageDialog(null,
                "Pieces Information:\n" +
                        "The Ram piece can only move forward, 1 step. If it reaches the end of the board, it turns around and\n" +
                        "starts heading back the other way. It cannot skip over other pieces.\n" +
                        "The Biz piece moves in a 3x2 L shape in any orientation (kind of like the knight in standard\n" +
                        "chess.) This is the only piece that can skip over other pieces.\n" +
                        "The Tor piece can move orthogonally only but can go any distance. It cannot skip over other pieces.\n" +
                        "However, after 2 turns, it transforms into the Xor piece.\n" +
                        "The Xor piece can move diagonally only but can go any distance. It cannot skip over other pieces.\n" +
                        "However, after 2 turns, it transforms into the Tor piece.\n" +
                        "The Sau piece can move only one step in any direction. \n" +
                        "None of the pieces are allowed to skip over other pieces, except for Biz.\n" +
                        "After 2 turns (counting one blue move and one red move as one turn), all Tor pieces will turn into Xor pieces, and all\n" +
                        "Xor pieces will turn into Tor pieces. \n\n" +
                        "Win Conditions: \n" +
                        "The game ends when the Sau is captured by the other side.\n",
                "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Manages the quit operation for the application.
     * This method is responsible for performing necessary cleanup tasks
     * and terminating the application when the user chooses to exit.
     * Specifically, it displays a farewell message to the user and
     * halts program execution by calling System.exit(0).
     * Coded by Lai Cal Wyn
     */
    private void handleQuit() {
        System.out.println("Thank you for playing!!");
        System.exit(0);
    }
}
