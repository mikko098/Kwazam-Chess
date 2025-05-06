package model;

import view.Board;

import java.util.ArrayList;

/**
 * Represents an abstract base class for different types of game pieces in a custom board game.
 * This class defines common properties and behaviors shared among all game pieces, such as
 * their position on the board, their owner, and methods to calculate valid moves based on
 * specific implementations of the game piece.
 *
 * It serves as the base class to be extended by specific piece types, which will implement
 * their unique movement rules using the abstract method `getMoves`.
 *
 * In the context of design patterns, this class functions as the Template Method pattern,
 * providing a structured framework for common functionalities while delegating the implementation
 * of specific movement logic to derived classes.
 * Coded by Chan Ga Wai
 */
public abstract class Piece {
    private int col, row;
    private final Player owner;

    public String name;

    /**
     * Constructs a new Piece with specified position and ownership.
     *
     * @param col The column position of the piece on the game board.
     * @param row The row position of the piece on the game board.
     * @param owner The player to whom the piece belongs.
     *              Coded by Chan Ga Wai
     */
    public Piece(int col, int row, Player owner) {
        this.col = col;
        this.row = row;
        this.owner = owner;
    }

    /**
     * Retrieves the name of the game piece.
     *
     * @return The name of the piece as a String.
     * Coded by Chan Ga Wai
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates the pixel-based X-coordinate of the piece on the game board.
     * The X-coordinate is determined by multiplying the column index of the piece
     * by the size of a square on the board.
     *
     * @return The X-coordinate (in pixels) of the piece's position on the game board.
     * Coded by Chan Ga Wai
     */
    public int getX(){
        return col * Board.SquareSize;
    }

    /**
     * Calculates the Y-coordinate for the piece's position on the board in pixels.
     * The Y-coordinate is derived by multiplying the row index of the piece
     * by the square size of the board.
     *
     * @return The Y-coordinate of the piece's position in pixels.
     * Coded by Chan Ga Wai
     */
    public int getY(){
        return row * Board.SquareSize;
    }

    /**
     * Retrieves the column position of the piece on the game board.
     *
     * @return The column (x-coordinate) of the piece.
     * Coded by Chan Ga Wai
     */
    public int getCol(){
        return col;
    }

    /**
     * Retrieves the row position of this piece on the game board.
     * The row position determines the vertical coordinate of the piece.
     *
     * @return The row index of this piece's position on the game board.
     * Coded by Chan Ga Wai
     */
    public int getRow(){
        return row;
    }

    /**
     * Sets the column position of the piece on the game board.
     *
     * @param col The new column position for the piece.
     *            Coded by Chan Ga Wai
     */
    public void setCol(int col){
        this.col = col;
    }

    /**
     * Updates the row position of the game piece on the board.
     *
     * @param row The new row position to set for the piece.
     *            Coded by Chan Ga Wai
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * Retrieves the owning player of this piece.
     *
     * @return The Player object that owns this game piece.
     * Coded by Chan Ga Wai
     */
    public Player getOwner(){
        return owner;
    }

    /**
     * Retrieves the name of the player who owns this piece.
     *
     * @return The owner's name as a string.
     * Coded by Chan Ga Wai
     */
    public String getOwnerName(){ return owner.getName(); }

    /**
     * Computes all valid moves for this piece based on its specific movement rules
     * and the given board configuration.
     *
     * @param board A 2D array of Tile objects representing the game board. Each Tile
     *              may contain a piece or be empty. This parameter enables the piece
     *              to determine valid moves by analyzing the state of the board.
     * @return An ArrayList of intPair objects, where each intPair represents a valid
     *         move. Each intPair contains the column (x-coordinate) and row (y-coordinate)
     *         of a valid destination based on the piece's movement rules and the board's state.
     *         Coded by Chan Ga Wai
     */
    public abstract ArrayList<intPair> getMoves(Tile[][] board);

    /**
     * Determines if the specified tile, identified by its coordinates (x, y),
     * is within the valid bounds of the game board.
     *
     * @param x The column index of the tile to be validated.
     * @param y The row index of the tile to be validated.
     * @return True if the tile is within the bounds of the game board,
     *         where 0 <= x < 5 and 0 <= y < 8; otherwise, false.
     *         Coded by Chan Ga Wai
     */
    public boolean isValidTile(int x, int y) {
        return ((0<=x && x<5) && (0<=y && y<8));
    }
}
