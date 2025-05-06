package model;

import java.util.ArrayList;

/**
 * Represents the "Biz" piece in a custom board game.
 * The Biz piece can move in an "L" shape, similar to the knight piece in chess,
 * with a fixed set of movement patterns determined by its implementation.
 *
 * This class extends the abstract Piece class and provides a concrete implementation
 * for its movement logic.
 *
 * In the context of design patterns, this class serves as a specific implementation
 * of the Template Method pattern, where the abstract method `getMoves` in the Piece
 * base class is implemented to define Biz-specific movement rules.
 * Coded by Lai Cal Wyn
 */
public class Biz extends Piece {
    public Biz(int col, int row, Player owner){
        super(col, row, owner);
        this.name = "Biz";
    }

    /**
     * Calculates all valid moves for the "Biz" piece based on its movement rules.
     * The "Biz" piece moves in an "L" shape, similar to the knight in chess,
     * considering the given board configuration.
     *
     * @param board A two-dimensional array representing the current state of the game board.
     *              Each element is a Tile object, which may contain another piece or be empty.
     * @return An ArrayList of intPair objects representing all valid positions to which
     *         the "Biz" piece can move based on its rules. Each intPair contains the column
     *         (x-coordinate) and the row (y-coordinate) of a valid move.
     *         Coded by Lai Cal Wyn
     */
    @Override
    public ArrayList<intPair> getMoves(Tile[][] board) {
        int[][] directions = {
                {2, 1},
                {2, -1},
                {-2, 1},
                {-2, -1},
                {1, 2},
                {1, -2},
                {-1, 2},
                {-1, -2}
        };
        ArrayList<intPair> moves = new ArrayList<>();
        for (int[] direction : directions) {
            if (isValidTile(direction[0] + getCol(), direction[1] + getRow())) {
                moves.add(new intPair(direction[0] + getCol(), direction[1] + getRow()));
            }
        }
        return moves;
    }
}