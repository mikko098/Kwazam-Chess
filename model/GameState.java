package model;

import controller.SaveLoadManager;

import java.util.ArrayList;

/**
 * Represents the state of the game, including the game board, players, pieces, and game-related information.
 * Implements the Singleton design pattern to ensure only one instance of the game state exists.
 * Manages all critical aspects of the game, such as game progression, moves, player turns, and piece transformations.
 */
public class GameState {
    private static GameState gamestate;
    private Tile[][] board;
    private int currentTurn;
    private int turnCount;
    private Player[] players;
    public int columns = 5;
    public int rows = 8;
    private boolean gameEnded = false;
    private String winner;
    private ArrayList<String> movesHistory = new ArrayList<>();

    public static ArrayList<Piece> pieces = new ArrayList<>();

    /**
     * Represents the state of the game including the board, players, and the game's progression.
     * The GameState class encapsulates all the critical elements required for managing the game's
     * state such as the board representation, list of players, and their associated pieces,
     * as well as methods to control the game's flow and logic.
     *
     * This class acts as the central component in the game's architecture where all events,
     * actions, and updates to the state are managed. It initializes the game board, sets up
     * the players, and orchestrates the placement of pieces and the turn-based flow of the game.
     *
     * In the context of game design, this class initiates the core gameplay configuration and
     * provides functionality to maintain the integrity of the game logic during execution.
     * Coded by Chan Ga Wai
     */
    public GameState() {

        board = new Tile[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = new Tile(i,j);
            }
        }

        players = new Player[]{new Player("P1"), new Player("P2")};

        setPieces();
        for (Piece piece : pieces) {
            board[piece.getCol()][piece.getRow()].setPiece(piece);
        }

        currentTurn = 0;
        turnCount = 1;

    }

    /**
     * Represents the state of the game, serving as the central class to manage and track
     * the game's progress, players, board, and other related data.
     *
     * This class follows the Singleton design pattern, ensuring that only one instance
     * of the GameState class exists throughout the application. The Singleton pattern
     * facilitates global access to the game's state and preserves its integrity.
     * Coded by Chan Ga Wai
     */
    public static GameState getInstance() {
        if (gamestate == null) {
            gamestate = new GameState();
        }
        return gamestate;
    }

    /**
     * Retrieves the current state of the game board.
     * The game board is represented as a two-dimensional array of Tile objects,
     * where each Tile corresponds to a specific position on the board and may contain a game piece
     * or be empty. This method provides access to the board for querying
     * Coded by Chan Ga Wai*/
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Represents the game state for a custom board game.
     * This class is a core component of the game, managing its current state, including turn tracking,
     * the game board, players, and related game actions.
     *
     * In the context of design patterns, this class could represent part of the Singleton pattern
     * if the game state is intended to have a single shared instance across the application.
     * Coded by Chan Ga Wai
     */
    public Player getPlayerTurn(){
        return players[currentTurn];
    }

    /**
     * Determines whether a move between two tiles is valid based on the ownership of the pieces
     * occupying the tiles. This method checks if both tiles have pieces owned by the same player.
     *
     * @param first The starting tile of the move. This tile should contain a piece.
     * @param second The destination tile of the move. This tile should contain a piece.
     * @return true if both tiles contain pieces owned by the same player; false otherwise.
     * Coded by Chan Ga Wai
     */
    public boolean checkValidMove(Tile first, Tile second) {
        return first.getPiece().getOwnerName().equals(second.getPiece().getOwnerName());
    }

    /**
     * Retrieves a list of valid moves for a given game piece, based on its specific movement rules
     * and the current state of the game board.
     *
     * This method delegates the logic for calculating valid moves to the specific piece's
     * implementation of the `getMoves` method. The resulting moves are calculated relative
     * to the piece's current position and the availability of positions on the board.
     *
     * @param piece The game piece for which valid moves are to be calculated. The piece must implement
     *              its specific movement logic through its `getMoves` method.
     * @return An ArrayList of intPair objects representing all possible moves the given piece can make
     *         on the board. Each intPair contains the column (x-coordinate) and row (y-coordinate) of
     *         a valid move position.
     *         Coded by Chan Ga Wai
     */
    public ArrayList<intPair> getValidMoves(Piece piece) {
        return piece.getMoves(board);
    }

    /**
     * Retrieves the list of all pieces currently present in the game.
     * This method provides access to the set of all game pieces being managed
     * within the current game state.
     *
     * @return An ArrayList of Piece objects representing all the game pieces
     * currently part of the game.
     * Coded by Chan Ga Wai
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Represents the state of the game, including the board, players, pieces, and the current turn.
     * This class manages the progression of the game and tracks its state at any given moment.
     *
     * In the context of design patterns, this class may act as a Singleton, providing a single
     * instance to manage the game's state and ensuring global access.
     * Coded by Chan Ga Wai
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Retrieves the current turn counter value for the game.
     * The turn counter tracks the total number of turns that have occurred in the game.
     *
     * @return The current turn counter as an integer.
     * Coded by Chan Ga Wai
     */
    public int getTurnCounter() {
        return turnCount;
    }

    /**
     * Retrieves a Player object from the game state based on the specified name.
     *
     * @param name The name of the player to be searched for within the game state.
     * @return The Player object with the matching name if found, or null if no such player exists.
     * Coded by Chan Ga Wai
     */
    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null; // If no matching player is found
    }

    /**
     * Sets the current turn in the game state.
     *
     * @param currentTurn The integer representing the current player's turn.
     *                    Coded by Chan Ga Wai
     */
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Sets the turn count for the current game state.
     *
     * @param turnCount The number of turns that have been taken in the game.
     *                  This value is used to track the progression of the game.
     *                  Coded by Chan Ga Wai
     */
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    /**
     * The GameState class represents the current state of the game. It manages the board,
     * players, pieces, turn information, game progress, and move history.
     * This class serves as the core of the game state management, allowing for saving,
     * loading, and manipulating the game state during gameplay.
     * Coded by Chan Ga Wai
     */
    public void saveBoard(String fileName){
        SaveLoadManager.saveBoard(pieces, getTurnCounter(), getCurrentTurn(), getMovesHistory(),fileName);
    }

    /**
     * Loads the game board from an external file into the current game state.
     * This method interacts with the SaveLoadManager utility class to retrieve
     * the saved game data, including the pieces on the board, move history,
     * turn count, and current turn. The retrieved data is then used to update
     * the current game state accordingly.
     *
     * @param filename The name of the file from which the game state should be loaded.
     *                 Coded by Chan Ga Wai
     */
    public void loadBoard(String filename){
        SaveLoadManager.loadBoard(filename, this);
    }

    /**
     * Determines if the game has ended.
     * This method checks the game state and returns whether the game has concluded,
     * typically based on win conditions, a stalemate, or other game-specific rules.
     *
     * @return true if the game is ended, false otherwise.
     * Coded by Chan Ga Wai
     */
    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     * Retrieves the winner of the game after it has concluded.
     * This method is typically used to identify the player who has won
     * after the game has reached its termination condition. If the game
     * has not ended, this method may return null or an empty string based
     * on implementation.
     * Coded by Chan Ga Wai
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Sets the game state to indicate that the game has ended and assigns
     * the winner of the game.
     *
     * @param winner The name of the player who won the game.
     *               Coded by Chan Ga Wai
     */
    public void setGameEnd(String winner) {
        this.gameEnded = true;
        this.winner = winner;
    }

    /**
     * Represents the state of the game at any point during its progression. This class
     * contains the game board, players, turn logic, and other aspects required to
     * track and manage the game's current state.
     *
     * This class acts as a core component managing the game's lifecycle, storing data
     * about moves, game outcome, and turn order. Additionally, it provides the necessary
     * methods to retrieve or modify the game state, making it a critical part of game
     * management logic.
     *
     * In a larger design context, this class could be considered centrally responsible
     * for enforcing game rules and facilitating the interaction between game components.
     * Coded by Chan Ga Wai
     */
    public ArrayList<String> getMovesHistory() {
        return movesHistory;
    }

    /**
     * Adds a move description to the game's move history. This method is used to
     * keep track of the sequence of moves made during the game, enabling features
     * like move review or undo functionality.
     *
     * @param moveDescription A string that describes the move made, typically
     *                        including details about the piece moved, its start
     *                        position, and its destination.
     *                        Coded by Chan Ga Wai
     */
    public void addMoveToHistory(String moveDescription) {
        movesHistory.add(moveDescription);
    }

    /**
     * Executes a transformation operation on all pieces within the `pieces` collection
     * of the game. Specifically, this method iterates through the list of pieces,
     * identifying pieces of type "Xor" and "Tor". It then replaces pieces of type "Xor"
     * with new instances of "Tor", and vice versa.
     *
     * The transformation includes the following steps:
     * 1. For each piece in the collection, check its name using the `getName` method.
     * 2. If the piece is named "Xor", replace it with a new "Tor" instance with the same
     *    column, row, and owner values as the original "Xor".
     * 3. If the piece is named "Tor", replace it with a new "Xor" instance with the same
     *    column, row, and owner values as the original "Tor".
     * 4. Update the game board to reflect the newly created
     * Coded by Harrish Panicker*/
    public void switchXorTor() {
        ArrayList<Piece> piecesToAdd = new ArrayList<>();
        ArrayList<Piece> piecesToRemove = new ArrayList<>();

        for (Piece piece : pieces) {
            if (piece.getName().equals("Xor")) {
                Piece newPiece = new Tor(piece.getCol(), piece.getRow(), piece.getOwner());
                piecesToRemove.add(piece);
                piecesToAdd.add(newPiece);
                board[piece.getCol()][piece.getRow()].setPiece(newPiece);
            } else if (piece.getName().equals("Tor")) {
                Piece newPiece = new Xor(piece.getCol(), piece.getRow(), piece.getOwner());
                piecesToRemove.add(piece);
                piecesToAdd.add(newPiece);
                board[piece.getCol()][piece.getRow()].setPiece(newPiece);
            }
        }

        // Apply the changes after iteration
        pieces.removeAll(piecesToRemove);
        pieces.addAll(piecesToAdd);
    }

    /**
     * Manages the game's state, including the board, players, turn tracking, and other gameplay mechanics.
     * This class handles the logic for various actions such as moving pieces, capturing pieces,
     * saving/loading the game state, and determining the game's outcome.
     *
     * In the context of a design pattern, this class functions as a central Controller
     * in implementing game logic, maintaining the state, and enabling interactions among various components.
     * Coded by Harrish Panicker
     */
    public void movePiece(Piece currentPiece, Tile destination) {
        // to be edited
        int currentRow = currentPiece.getRow();
        int currentCol = currentPiece.getCol();

        board[currentCol][currentRow].setPiece(null);

        destination.setPiece(currentPiece);
        currentPiece.setRow(destination.getRow());
        currentPiece.setCol(destination.getCol());

        currentTurn = (currentTurn + 1) % players.length;

        if (currentTurn == 0){
            turnCount++;
            if ((turnCount % 2) == 1){
                System.out.println("Transforming Xor and Tor.");
                switchXorTor();
            }
        }
    }

    /**
     * Represents the state of the game, including the board, players, pieces,
     * and various game-related actions like moving, capturing, and ending the game.
     * This class is part of the Singleton design pattern, ensuring a single instance
     * of the game state is maintained.
     * Coded by Harrish Panicker
     */
    public void capturePiece(Piece currentPiece, Tile destination) {
        pieces.remove(destination.getPiece());
        movePiece(currentPiece, destination);
    }


    /**
     * This method checks whether a specific tile on the game board is occupied by a piece.
     *
     * @param col The column index of the tile to check.
     * @param row The row index of the tile to check.
     * @return true if the specified tile is occupied; false otherwise.
     * Coded by Harrish Panicker
     */
    public boolean checkOccupied(int col, int row) {
        return board[col][row].isOccupied();
    }

    /**
     * Retrieves the piece located at the specified position on the game board.
     *
     * @param col The column index of the tile from which to retrieve the piece.
     * @param row The row index of the tile from which to retrieve the piece.
     * @return The Piece object at the specified position, or null if no piece occupies the tile.
     * Coded by Harrish Panicker
     */
    public Piece getPiece(int col, int row) {
        return board[col][row].getPiece();
    }

    /**
     * Retrieves a specific tile from the game board based on the provided column and row indices.
     * This method allows access to the internal grid of Tiles that represent the board's structure.
     * It is typically used to perform operations on or retrieve information about a particular
     * location within the board, such as checking its state or modifying it.
     *
     * @param col The column index of the desired Tile on the board.
     * @param row The row index of the desired Tile on the board.
     * @return The Tile object located at the specified column
     * Coded by Harrish Panicker*/
    public Tile getTile(int col, int row) {
        return board[col][row];
    }

    /**
     * The GameState class encapsulates the state of a custom board game. It maintains
     * the game's current turn, the board, the list of pieces, players, and other game-specific
     * attributes such as the winner, turn count, and move history. This class is central to
     * managing the game's logic, flow, and data state.
     *
     * As part of the Singleton design pattern, this class ensures that only one instance
     * of the game state exists, and provides global access to it via the getInstance method.
     * Coded by Harrish Panicker
     */
    public void setPieces(){
        pieces.add(new Ram(0,6, players[0]));
        pieces.add(new Ram(1,6, players[0]));
        pieces.add(new Ram(2,6, players[0]));
        pieces.add(new Ram(3,6, players[0]));
        pieces.add(new Ram(4,6, players[0]));
        pieces.add(new Xor(0,7, players[0]));
        pieces.add(new Biz(1,7, players[0]));
        pieces.add(new Sau(2,7, players[0]));
        pieces.add(new Biz(3,7, players[0]));
        pieces.add(new Tor(4,7, players[0]));

        pieces.add(new Ram(0,1, players[1]));
        pieces.add(new Ram(1,1, players[1]));
        pieces.add(new Ram(2,1, players[1]));
        pieces.add(new Ram(3,1, players[1]));
        pieces.add(new Ram(4,1, players[1]));
        pieces.add(new Tor(0,0, players[1]));
        pieces.add(new Biz(1,0, players[1]));
        pieces.add(new Sau(2,0, players[1]));
        pieces.add(new Biz(3,0, players[1]));
        pieces.add(new Xor(4,0, players[1]));
    }

    /**
     * Resets the game state to its initial configuration.
     *
     * This method is responsible for reinitializing the game by clearing the board, resetting the
     * player turns, game state variables, and moves history. It ensures that all game pieces are
     * returned to their starting positions and that the board is set up with new Tiles and players.
     * This functionality is used to restart gameplay while maintaining the consistent rules and
     * starting conditions of the game.
     *
     * Internally, it performs the following:
     * - Clears the list
     * Coded by Harrish Panicker*/
    public void restartGame(){
        pieces.clear();
        winner = "";
        gameEnded = false;

        board = new Tile[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = new Tile(i,j);
            }
        }

        players = new Player[]{new Player("P1"), new Player("P2")};

        setPieces();
        for (Piece piece : pieces) {
            board[piece.getCol()][piece.getRow()].setPiece(piece);
        }
        turnCount = 1;
        currentTurn = 0;

        movesHistory.clear();
    }

}
