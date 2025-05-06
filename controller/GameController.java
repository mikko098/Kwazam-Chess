package controller;

import model.*;
import view.Board;

import java.util.ArrayList;

/**
 * The GameController class serves as the main controller for managing the state and interactions
 * within the game. It is an implementation of the Subject in the Observer design pattern,
 * notifying observers whenever there is a change in the game's state.
 *
 * Additionally, it implements the Mouse.MouseClickHandler interface to handle user input
 * through mouse clicks, interpreting them as interactions with the game board.
 *
 * This class handles the logic for piece selection, movement, capturing, determining the game's end,
 * and switching turns between players. It also provides methods for saving, loading, and restarting the game.
 * This entire GameController.java file is coded by Mishal Mann Nair
 */
public class GameController extends Subject implements Mouse.MouseClickHandler{
    private GameState gameState;
    private Piece currentPiece;
    private static GameController gameController;
    private String winner;
    public boolean resetTimer;

    /**
     * The GameController class manages the primary game logic and serves as
     * the controller in an MVC architecture. It acts as the central mediator
     * for interactions between the game view and the underlying game state,
     * coordinating actions and game mechanics.
     *
     * The GameController interacts heavily with GameState to query and modify
     * the state of the game, manage piece movements, handle interactions with
     * the board, and determine game-ending conditions.
     *
     * Implements the singleton design pattern to ensure there is only one
     * instance of the game controller managing the game at a time.
     * Coded by Mishal Mann Nair
     */
    public GameController() {
        gameState = GameState.getInstance();
        currentPiece = null;
        winner = null;
//        this.timerController = new TimerController(600);
    }

    /**
     * Represents the GameController class responsible for managing the overall game state,
     * including pieces, tiles, moves, and player turns. This class acts as the central
     * controller in the game's MVC architecture and implements the Singleton design pattern
     * to ensure a single, global instance is used throughout the application. It handles
     * game logic, interactions, and manages communication between various components.
     * Coded by Mishal Mann Nair
     */
    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    /**
     * Retrieves the game piece located at the specified column and row on the game board.
     * This method interacts with the game state to access the piece at a given position
     * and returns the Piece object if one exists, or null if no piece is present.
     *
     * @param col The column index of the tile from which the piece is to be retrieved.
     * @param row The row index of the tile from which the piece is to be retrieved.
     * @return The Piece object located at the specified board position, or null if no piece is present.
     * Coded by Mishal Mann Nair
     */
    public Piece getPiece(int col, int row) {
        return gameState.getPiece(col, row);
    }

    /**
     * Retrieves a Tile object from the game board at the specified column and row.
     * The method delegates the retrieval operation to the gameState, which manages
     * the current state of the game board.
     *
     * @param col The column index of the tile to be retrieved.
     * @param row The row index of the tile to be retrieved.
     * @return The Tile object located at the specified column and row on the game board.
     * Coded by Mishal Mann Nair
     */
    public Tile getTile(int col, int row) {
        return gameState.getTile(col, row);
    }

    /**
     * Represents a controller for managing the state and behavior of a game,
     * including player interactions, game piece movements, and game logic.
     * This class acts as the main coordinator between the game model and
     * the user interface, ensuring the proper flow and update of the game state.
     *
     * In the context of design patterns, this class serves as a Controller
     * in the MVC pattern. It mediates input and updates between the view and model.
     * Coded by Mishal Mann Nair
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * The GameController class acts as the main mediator for gameplay operations,
     * encapsulating game logic and interactions between components.
     * It implements the Singleton design pattern to ensure only one instance of the game controller exists.
     * Coded by Mishal Mann Nair
     */
    public static ArrayList<String> getMovesHistory() {
        return GameState.getInstance().getMovesHistory();
    }

    /**
     * Represents the controller for a game, managing the game state, player interactions,
     * and piece movements. It serves as the mediator between the view (e.g., GUI or user input)
     * and the underlying game logic, encapsulating the game management functionality.
     *
     * This class adheres to the MVC (Model-View-Controller) pattern, taking the role of the Controller.
     * It interprets user input, manipulates the model (game state), and updates the view when necessary.
     * Coded by Mishal Mann Nair
     */
    public ArrayList<intPair> getMoves() {
        return currentPiece.getMoves(gameState.getBoard());
    }

    /**
     * GameController is responsible for managing the game state, player interactions,
     * and overall game logic. It acts as the primary controller in the implementation
     * of the Model-View-Controller (MVC) design pattern, orchestrating operations
     * between the model (game data) and view (user interface).
     *
     * This class handles game operations such as validating moves, interacting with tiles,
     * tracking turns, and notifying the end of the game.
     * Coded by Mishal Mann Nair
     */
    public boolean isValidTile(int x, int y) {
        return ((0<=x && x<5) && (0<=y && y<8));
    }

    /**
     * Represents the controller for the game, responsible for managing game state,
     * handling user interactions, and maintaining the flow of the gameplay logic.
     * This class acts as the central hub for orchestrating interactions between
     * the model, view, and controller components of the application.
     *
     * This class follows the Singleton design pattern to ensure that only one
     * instance of the game controller exists throughout the application’s lifecycle.
     * Coded by Mishal Mann Nair
     */
    public int getCurrentTurn() {
        return gameState.getCurrentTurn();
    }

    /**
     * Represents a controller responsible for managing the flow of the game,
     * including the interaction between the game state and the user interface.
     * Part of the Model-View-Controller pattern, this class acts as the Controller.
     * Coded by Mishal Mann Nair
     */
    public int getTurnCount(){
        return gameState.getTurnCounter();
    }

    /**
     * Moves the specified game piece to the target destination tile. This method updates
     * the game state by performing the piece movement, logs the move into the game's move history,
     * and notifies observers of the change. It ensures that the game board reflects the new position
     * of the piece and triggers any necessary updates or reactions in response to the movement.
     *
     * @param currentPiece The game piece to be moved.
     * @param destination The tile to which the game piece is to be moved.
     * Coded by Mishal Mann Nair
     */
    public void movePiece(Piece currentPiece, Tile destination) {
        gameState.movePiece(currentPiece, destination);
        gameState.addMoveToHistory(currentPiece.getName() + " " + displayX(destination.getCol()) + displayY(destination.getRow()));
        notifyObservers();
    }

    /**
     * Handles the logic for capturing a piece on the game board when a move is made.
     * This method updates the game state by removing the captured piece, recording
     * the move in the game history, and checking for game-ending conditions such as
     * capturing the opponent's "Sau" piece. Observers are notified of the state change.
     *
     * @param currentPiece The piece belonging to the current player that performs the capture.
     * @param destination The tile to which the current piece moves and captures the opponent's piece.
     * Coded by Mishal Mann Nair
     */
    public void capturePiece(Piece currentPiece, Tile destination) {
        Piece capturedPiece = gameState.getPiece(destination.getCol(), destination.getRow());
        gameState.capturePiece(currentPiece, destination);
        gameState.addMoveToHistory(currentPiece.getName() + " x "  + displayX(destination.getCol()) + displayY(destination.getRow()));


        // Check if the captured piece is "Sau"
        if (capturedPiece.getName().equals("Sau")) {
            gameState.setGameEnd(currentPiece.getOwnerName()); // Notify the game end
            notifyGameEnd();
            return;
        }

        notifyObservers();
    }

    /**
     * Checks whether a specific tile on the game board is occupied.
     * This method interacts with the game state to verify if the
     * tile at the specified column and row contains a piece.
     *
     * @param col The column index of the tile to check.
     * @param row The row index of the tile to check.
     * @return true if the tile at the specified position is occupied, false otherwise.
     * Coded by Mishal Mann Nair
     */
    public boolean isOccupied(int col, int row) {
        return gameState.checkOccupied(col, row);
    }

    /**
     * Retrieves the list of all game pieces currently present in the game state.
     * This method serves as an intermediary between the GameController and GameState
     * to provide access to the pieces being managed by the game. It is used for
     * operations that require knowledge of all active game pieces, such as rendering
     * the board, validating moves, or determining game logic outcomes.
     *
     * @return An ArrayList of Piece objects representing all active pieces managed
     *         in the current game state.
     *         Coded by Mishal Mann Nair
     */
    public ArrayList<Piece> getPieces(){
        return gameState.getPieces();
    }

    /**
     * Handles user mouse click events on the game board.
     * Determines the appropriate interaction based on the current player's turn
     * and the coordinates of the click, and delegates the event handling to the
     * corresponding logic for interacting with the game board.
     *
     * @param x The x-coordinate of the mouse click, representing the horizontal position on the board.
     * @param y The y-coordinate of the mouse click, representing the vertical position on the board.
     *          Coded by Mishal Mann Nair
     */
    public void onMouseClick(int x, int y) {
        if (getCurrentTurn() % 2 == 0)
            boardInteract(x, y);
        else
            boardInteract(5 * Board.SquareSize - x, 8 * Board.SquareSize - y);
    }

    /**
     * Converts a given column index into its corresponding alphabetical representation.
     * The method is commonly used for converting numerical column indices into a format
     * that is more user-readable, often corresponding to game board column labels.
     *
     * @param col The column index to be converted into its alphabetical representation.
     * @return The character representation of the column index, starting from 'A'.
     * Coded by Mishal Mann Nair
     */
    public char displayX(int col) {
        return (char)(col + 'A');
    }

    /**
     * Calculates and returns the display index for a given row in the game board.
     * This method adjusts the provided row index to match the 1-based indexing
     * commonly used in user interfaces or game displays.
     *
     * @param row The row index (0-based) to be adjusted for display purposes.
     * @return The 1-based row index used for display.
     * Coded by Mishal Mann Nair
     */
    public int displayY(int row) {
        return (row + 1);
    }

    /**
     * Determines whether it is the given player's turn to play in the game.
     * This method compares the name of the current player whose turn it
     * currently is, as maintained by the game state, with the name of
     * the provided Player object.
     *
     * @param player The Player object to check if it is their turn.
     * @return true if it is the provided player's turn, false otherwise.
     * Coded by Mishal Mann Nair
     */
    public boolean isPlayerTurn(Player player) {
        String currentPlayerName = gameState.getPlayerTurn().getName();
        return currentPlayerName.equals(player.getName());
    }

    /**
     * Notifies the end of the game by determining the winner and updating all registered observers.
     *
     * This method fetches the winner of the game by querying the current game state, stores it
     * in the appropriate field, and notifies all observers that the game has concluded. Observers
     * are responsible for responding to this notification, which could involve updating the user
     * interface, displaying final game results, or triggering post-game operations.
     *
     * This method acts as part of the observer design pattern, where GameController is
     * the Subject, and its observers, such as GameContainer or other UI components,
     * are notified of relevant state changes.
     * Coded by Mishal Mann Nair
     */
    public void notifyGameEnd() {
        winner = gameState.getWinner();
        notifyObservers(); // Notify the observer (GameContainer)
    }

    /**
     * Determines whether the game has ended by querying the current game state.
     *
     * This method delegates the check to the gameState object, which encapsulates
     * the logic for tracking the game's completion status. The result of this query
     * indicates if the game has reached its conclusion according to the rules and
     * conditions defined in the game logic.
     *
     * @return true if the game has ended, otherwise false.
     * Coded by Mishal Mann Nair
     */
    public boolean isGameEnded(){
        return gameState.isGameEnded();
    }

    /**
     * Retrieves the name or identifier of the winner of the game.
     * This method provides access to the final result of the game by
     * returning the winner, which is stored as part of the game state.
     *
     * If the game has not yet concluded, the returned value may be null
     * or an empty string, depending on the implementation.
     * Coded by Mishal Mann Nair
     */
    public String getWinner() {
        return winner;
    }


    /**
     * Handles interactions with the game board based on the player's click coordinates.
     * This method determines the context of the interaction (e.g., selecting a piece,
     * moving a piece, interacting with occupied tiles) and acts accordingly by modifying
     * the game state or invoking appropriate helper methods for the action.
     *
     * @param x The x-coordinate of the player's click on the game board.
     * @param y The y-coordinate of the player's click on the game board.
     *          Coded by Mishal Mann Nair
     */
    public void boardInteract(int x, int y) {

        // Ensure the click is within the board's bounds
        if (x > 5 * Board.SquareSize || y > 8 * Board.SquareSize || x < 0 || y < 0) {
            return;
        }

        // Calculate the column and row based on the click position
        int row = y / Board.SquareSize;
        int col = x / Board.SquareSize;

        // Case 1: No piece selected and clicked on an empty tile
        if (currentPiece == null && !isOccupied(col, row) || !isValidTile(col, row)) {
            return;
        }

        // Case 2: Selecting a piece to move
        if (currentPiece == null && isOccupied(col, row)) {
            if (isPlayerTurn(getPiece(col,row).getOwner())) {
                currentPiece = getPiece(col, row);
                notifyObservers();
            }
            return;
        }

        // Case 3: Moving the currently selected piece to an empty tile
        if (currentPiece != null && !isOccupied(col, row)) {
            handleMoveToEmptyTile(col, row);
            return;
        }

        // Case 4: Handling a click on a tile occupied by another piece
        if (currentPiece != null && isOccupied(col, row)) {
            handleInteractionWithOccupiedTile(col, row);
        }
    }
    /**
     * Handles the action of moving a game piece to an empty tile on the game board.
     * This private utility method verifies the validity of the move based on the
     * current game state and, if valid, executes the move by interacting with
     * the game board's state and piece positions. If the move is invalid, it outputs
     * an appropriate message.
     *
     * This method is used internally by the GameController to manage piece movements
     * as part of the game's logic.
     *
     * @param col The column index of the target tile.
     * @param row The row index of the target tile.
     *            Coded by Mishal Mann Nair
     */
    // Helper method to handle moving a piece to an empty tile
    private void handleMoveToEmptyTile(int col, int row) {

        // Check if the move is valid
        for (intPair validMove : gameState.getValidMoves(currentPiece)) {
            if (col == validMove.getX() && row == validMove.getY()) {
                Tile destinationTile = getTile(col, row);
                movePiece(currentPiece, destinationTile);
                currentPiece = null; // Deselect the current piece
                return;
            }
        }
        System.out.println("Invalid Move.");
    }

    /**
     * Handles the interactions when a player interacts with a tile that is occupied by another piece.
     * This private helper method determines the appropriate course of action based on the ownership
     * of the piece on the tile and whether a valid capture attempt is being made.
     * It manages two primary scenarios: switching the selected piece if it belongs to the same player,
     * or attempting to capture an opponent's piece if the move is valid.
     *
     * @param col The column index of the tile being interacted with.
     * @param row The row index of the tile being interacted with.
     *            Coded by Mishal Mann Nair
     */
    // Helper method to handle interactions with a tile occupied by another piece
    private void handleInteractionWithOccupiedTile(int col, int row) {
        Piece clickedPiece = getPiece(col, row);

        // Case 4a: Clicking on a piece owned by the same player
        if (clickedPiece.getOwnerName().equals(currentPiece.getOwnerName())) {
            System.out.println("Same Owner. Switching selection.");
            currentPiece = clickedPiece;
            notifyObservers();
            return;
        }

        // Case 4b: Attempting to capture an opponent's piece

        for (intPair validMove : gameState.getValidMoves(currentPiece)) {
            if (col == validMove.getX() && row == validMove.getY()) {
                Tile destinationTile = getTile(col, row);
                System.out.println("Captured Piece.");
                capturePiece(currentPiece, destinationTile);
                currentPiece = null; // Deselect the current piece
                return;
            }
        }
        System.out.println("Invalid Capture Attempt.");
    }

    /**
     * Saves the current state of the game to a file.
     * This method delegates the task of saving the game board and relevant
     * game data, such as piece positions, move history, and turn information,
     * to the gameState's saveBoard functionality.
     *
     * @param fileName The name of the file where the game state will be saved.
     *                 Coded by Mishal Mann Nair
     */
    public void saveGame(String fileName) {
        gameState.saveBoard(fileName);
    }

    /**
     * Loads a saved game state from a specified file and updates the game observers
     * to reflect any changes in the game's visual or logical state.
     *
     * @param fileName The name of the file from which the game state will be loaded.
     *                 Coded by Mishal Mann Nair
     */
    public void loadGame(String fileName) {
        gameState.loadBoard(fileName);
        notifyObservers();
    }

    /**
     * Restarts the current game by reinitializing the game state, resetting the timer,
     * and notifying all observers about the changes.
     *
     * Delegates the game reinitialization process to the gameState's restartGame method,
     * which clears previous game data, resets the board, repositions all game pieces,
     * and initializes the turn count and other state-related fields.
     *
     * Ensures that all observing components, such as the view, are updated by invoking the notifyObservers method.
     * Coded by Mishal Mann Nair
     */
    public void restartGame() {
        gameState.restartGame();
        resetTimer = true;
        notifyObservers();
    }

}