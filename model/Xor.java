package model;

import java.util.ArrayList;

/**
 * The Xor class represents a specific type of game piece in a custom board game.
 * It extends the abstract `Piece` class and implements its unique movement rules.
 *
 * The Xor piece moves diagonally in all directions (north-east, south-east, south-west,
 * and north-west) until it encounters an occupied tile or the edge of the game board.
 * It can capture opponent pieces by moving onto their occupied tiles.
 *
 * In the context of the game's design, this class encapsulates the behavior and rules
 * specific to the Xor piece's movement, adhering to the polymorphic structure of the
 * `Piece` base class.
 * Coded by Harrish Panicker
 */
public class Xor extends Piece {
    public Xor(int col, int row, Player owner){
        super(col, row, owner);
        this.name = "Xor";
    }

    /**
     * Calculates and retrieves all valid moves for the current piece on a given game board.
     * This method checks in all diagonal directions (north-east, south-east, south-west,
     * north-west) and gathers all reachable positions until an occupied tile or edge of the
     * board is encountered.
     *
     * A valid move is defined as an empty tile or a tile occupied by an opponent's piece.
     *
     * @param board A 2D array of Tile objects representing the current state of the game board.
     *              Each Tile may be empty or occupied by a piece.
     * @return An ArrayList containing intPair objects, where each intPair represents a valid move
     *         as a pair of coordinates (x, y) on the board.
     *         Coded by Harrish Panicker
     */
    @Override
    public ArrayList<intPair> getMoves(Tile[][] board) {
        ArrayList<intPair> moves = new ArrayList<>();
        int[][] directions = {
                {1, 1},  // north-east
                {1, -1}, // south-east
                {-1, -1}, // south-west
                {-1, 1}  // north-west
        };

        for (int[] direction : directions) {
            int x = getCol() + direction[0];
            int y = getRow() + direction[1];
            while (isValidTile(x, y) && board[x][y].getPiece() == null) {
                moves.add(new intPair(x, y));
                x += direction[0];
                y += direction[1];
            }
            if (isValidTile(x, y) && board[x][y].getPiece().getOwner() != getOwner()) {
                moves.add(new intPair(x, y));
            }
        }
        return moves;
    }
}