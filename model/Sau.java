package model;

import java.util.ArrayList;

/**
 * Represents the Sau game piece, a specific type of piece in the board game.
 * The Sau piece has its own unique movement rules, allowing it to move in all
 * eight primary directions (north, south, east, west, and the diagonal directions)
 * provided the destination tile is valid and either empty or occupied by an opposing piece.
 *
 * This class extends the Piece abstract base class and overrides the `getMoves` method
 * to define its unique movement logic. It leverages the framework for movement and position
 * validation provided by the base class.
 *
 * In the context of a game, this class encapsulates the specific functionality and
 * behaviors for the Sau piece while adhering to the overall design principles of the game system.
 * Coded by Harrish Panicker
 */
public class Sau extends Piece {
    public Sau(int col, int row, Player owner){
        super(col, row, owner);
        this.name = "Sau";
    }

    @Override
    public ArrayList<intPair> getMoves(Tile[][] board) {
        ArrayList<intPair> moves = new ArrayList<>();
        int[][] directions = {
                {1, 0},  // north-east
                {1, 1},  // east
                {0, 1},   // south-east
                {-1, 1}, // south-west
                {-1, 0}, // west
                {-1, -1},// north-west
                {0, -1}, // north-east
                {1, -1}  // east
        };

        for (int[] direction : directions) {
            int x = getCol() + direction[0];
            int y = getRow() + direction[1];
            if (isValidTile(x, y) && board[x][y].getPiece() == null) {
                moves.add(new intPair(x, y));
            }
            else if (isValidTile(x, y) && board[x][y].getPiece().getOwner() != (getOwner())) {
                moves.add(new intPair(x, y));
            }
        }
        return moves;
    }
}