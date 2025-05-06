package model;

/**
 * Represents a single tile on a game board.
 * This class is a fundamental component for organizing the grid structure of the game board.
 * Each tile is identified by its column and row position and may optionally hold a game piece.
 *
 * This class is utilized to model individual cells of the board, providing functionality
 * to manage the state such as checking if a piece occupies the tile.
 * In a game design, Tile works in collaboration with the Piece class and other components
 * to represent and manage gameplay.
 * Coded by Chan Ga Wai
 */
public class Tile {
    private int col;
    private int row;
    private Piece piece;

    /**
     * Represents a single tile on the board.
     * Each tile holds its column and row position and optionally contains a piece.
     * This class primarily represents the "Cell" concept in the game board
     * context and serves as a base container for pieces.
     * In the context of game design, it works in collaboration with other game pieces to model the board.
     *
     * @param col The column index of the tile on the board.
     * @param row The row index of the tile on the board.
     *            Coded by Chan Ga Wai
     */
    public Tile(int col, int row) {
        this.col = col;
        this.row = row;
        this.piece = null;
    }

    /**
     * Represents a single tile on the game board. Each tile is defined by its column and row
     * coordinates and may optionally hold a game piece. The Tile class is utilized to organize
     * the grid structure of the game board and provide functionality for managing the state of
     * individual cells on the board, such as determining if they are occupied by a piece.
     *
     * This constructor initializes a Tile object with a specified column, row, and an optional
     * game piece.
     *
     * @param col The column index of the tile on the game board.
     * @param row The row index of the tile on the game board.
     * @param piece The game piece placed on the tile, or null if the tile is empty.
     *              Coded by Chan Ga Wai
     */
    public Tile(int col, int row, Piece piece) {
        this.col = col;
        this.row = row;
        this.piece = piece;
    }

    /**
     * Retrieves the column index of this Tile.
     *
     * @return the integer value representing the column position of this Tile on the board.
     * Coded by Chan Ga Wai
     */
    public int getCol() {
        return col;
    }

    /**
     * Retrieves the current row position of the tile.
     *
     * @return the integer value representing the row where this tile is located.
     * Coded by Chan Ga Wai
     */
    public int getRow() {
        return row;
    }

    /**
     * Determines whether this tile is occupied by a piece.
     *
     * @return true if the tile contains a piece; false otherwise.
     * Coded by Chan Ga Wai
     */
    public boolean isOccupied() {
        return piece != null;
    }

    /**
     * Retrieves the game piece currently assigned to this tile.
     * If the tile is not occupied, this method will return null.
     *
     * @return The Piece object occupying this tile, or null if the tile is unoccupied.
     * Coded by Chan Ga Wai
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets a Piece on the current Tile. This method is used to place or update the game piece
     * occupying the Tile.
     *
     * @param piece The Piece to be placed on the Tile. This can be a specific game piece
     *              or null if the Tile is to be made empty.
     *              Coded by Chan Ga Wai
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
