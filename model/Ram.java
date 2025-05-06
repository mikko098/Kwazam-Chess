package model;

import java.util.ArrayList;

/**
 * Represents the "Ram" piece in a custom board game. The Ram piece has distinctive
 * movement mechanics, moving vertically based on a promotion multiplier. This multiplier
 * changes upon reaching the topmost or bottommost rows of the board, enabling dynamic
 * gameplay interactions.
 *
 * This class extends the abstract Piece class, inheriting common functionality while
 * implementing its own movement rules. The movement logic is centered around promotion
 * mechanics and dynamic position validation, tailored specifically to the unique behavior
 * of the Ram piece.
 *
 * In the context of the Template Method design pattern, this class provides a concrete
 * implementation of the abstract `getMoves` method defined in the Piece base class. This
 * ensures encapsulation of the Ram-specific movement rules while adhering to the common
 * framework established by the Piece superclass.
 * Coded by Mishal Mann Nair
 */
public class Ram extends Piece {
    int promotionMultiplier;

    /**
     * Constructs a new Ram object, representing the "Ram" piece in the game, with a specific
     * position on the board and an owner.
     * The "Ram" piece has unique gameplay characteristics, including a promotion multiplier
     * that is influenced by reaching the edges of the game board.
     *
     * @param col The column position of the Ram piece on the game board.
     * @param row The row position of the Ram piece on the game board.
     * @param owner The player who owns this Ram piece.
     *              Coded by Mishal Mann Nair
     */
    public Ram(int col, int row, Player owner){
        super(col, row, owner);
        this.name = "Ram";
        this.promotionMultiplier = (getOwnerName().equals("P1") ? 1 : -1);
    }

    /**
     * Determines whether the current piece is positioned on the topmost row of the game board.
     * The topmost row is considered to have an index of 0.
     *
     * @return true if the piece is located in the topmost row of the board, false otherwise.
     * Coded by Mishal Mann Nair
     */
    public boolean isTopRow(){
        return getRow() == 0;
    }

    /**
     * Determines if the current piece is positioned on the bottom row of the board.
     *
     * @return true if the piece's row index is 7, indicating it is on the bottom row;
     *         false otherwise.
     *         Coded by Mishal Mann Nair
     */
    public boolean isBottomRow(){
        return getRow() == 7;
    }

    /**
     * Represents the "Ram" piece in a custom board game.
     * The Ram piece has unique movement rules where it moves vertically based on a promotion multiplier,
     * which changes when reaching the top or bottom row of the board.
     *
     * This class extends the abstract Piece class and provides a concrete implementation
     * for its movement logic.
     *
     * In the context of design patterns, this class serves as a specific implementation
     * of the Template Method pattern, where the abstract method `getMoves` in the Piece
     * base class is implemented to define Ram-specific movement rules.
     * Coded by Mishal Mann Nair
     */
    @Override
    public ArrayList<intPair> getMoves(Tile[][] board) {
        ArrayList<intPair> moves = new ArrayList<>();

        // Update promotionMultiplier only once when reaching the edge
        if (isTopRow() && promotionMultiplier > 0) {
            promotionMultiplier = -1;
        }

        if(isBottomRow() && promotionMultiplier < 0) {
            promotionMultiplier = 1;
        }

        // Calculate the next move based on the updated promotionMultiplier
        int row = getRow() - promotionMultiplier;
        int col = getCol();

        // Ensure the move is within bounds
        if (isValidTile(col, row)) {
            moves.add(new intPair(col, row));
        }

        return moves;
    }
}
