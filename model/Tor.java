package model;

import java.util.ArrayList;

/**
 * Represents the "Tor" piece in the game, inheriting from the abstract "Piece" class.
 * The "Tor" piece moves in a straight line across the board in four diagonal directions:
 * north-east, south-east, south-west, and north-west. The movement is restricted
 * based on board boundaries, the presence of other pieces, and specific game rules
 * for capturing opponent pieces.
 *
 * This class implements the movement rules specific to the "Tor" piece by overriding
 * the abstract getMoves method from the Piece class.
 * Coded by Harrish Panicker
 */
public class Tor extends Piece {
    public Tor(int col, int row, Player owner){
        super(col, row, owner);
        this.name = "Tor";
    }

    /**
     * Calculates and returns all possible moves for the "Tor" game piece.
     * The moves are determined based on the current state of the game board
     * and the movement rules defined for the piece. The "Tor" moves in a
     * straight path in four directions (north-east, south-east, south-west, north-west)
     * and can continue moving in a direction until it encounters a boundary,
     * an opponent's piece, or its own piece.
     *
     * @param board The current state of the game board, represented as a 2D array of Tiles,
     *              where each Tile may or may not contain a Piece.
     * @return An ArrayList of intPair objects, where each intPair represents the coordinates
     *         (column and row indices) of a valid tile the "Tor" can move to.
     *         Coded by Harrish Panicker
     */
    @Override
    public ArrayList<intPair> getMoves(Tile[][] board) {
        ArrayList<intPair> moves = new ArrayList<>();
        int[][] directions = {
                {1, 0},  // north-east
                {0, -1}, // south-east
                {-1, 0}, // south-west
                {0, 1}  // north-west
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
