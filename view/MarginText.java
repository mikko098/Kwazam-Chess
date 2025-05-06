package view;

import controller.GameController;

import java.awt.*;

/**
 * The MarginText class is responsible for rendering margin labels around a game board.
 * Margin labels include row numbers and column letters, which help players identify board positions.
 * This class uses the current game state, provided by the GameController, to dynamically render the
 * appropriate labels for each turn.
 *
 * It serves as a utility to enhance the user interface by aligning the labels with the game
 * board's grid system.
 * Coded by Chan Ga Wai
 */
public class MarginText {
    private final GameController gameController;
    int cols = 5;
    int rows = 8;
    public static float fontSize = 16f;

    MarginText(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Renders the margin labels (row numbers and column letters) around the game board.
     * This method uses graphical rendering to dynamically position and draw the labels
     * according to the current state of the game.
     *
     * @param g2 The graphics context used for rendering the text labels.
     *           Coded by Chan Ga Wai
     */
    public void draw(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(fontSize)); // Set a readable font size
        g2.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < cols; i++) {
            String colNumber = getColNumber(i);
            int x = i * Board.SquareSize + Board.SquareSize / 2 - g2.getFontMetrics().stringWidth(colNumber) / 2;
            int y = Board.SquareSize * rows + Board.SquareSize / 3; // Padding from the top
            g2.drawString(colNumber, x, y);
        }

        for (int j = 0; j < rows; j++) {
            String rowNumber = getRowNumber(j);
            int x = Board.SquareSize * cols + Board.SquareSize / 4; // Padding from the left
            int y = j * Board.SquareSize + Board.SquareSize / 2 + g2.getFontMetrics().getHeight() / 4;
            g2.drawString(rowNumber, x, y);
        }
    }

    /**
     * Determines the column label based on the given column index and the current player's turn.
     * The method adjusts the label format depending on whether the first player or the second
     * player is taking their turn, using alphabetical notation.
     *
     * @param col the zero-based column index for which the label needs to be determined.
     * @return the column label as a string for the first turn or reversed for subsequent turns.
     * Coded by Chan Ga Wai
     */
    public String getColNumber(int col){
        return gameController.getCurrentTurn() == 0 ? String.valueOf((char) (col + 65)) : String.valueOf((char) (64 + cols - col));
    }

    /**
     * Determines the row label based on the given row index and the current player's turn.
     * The method adjusts the label format depending on whether the first player or the second
     * player is taking their turn.
     *
     * @param row the zero-based row index for which the label needs to be determined.
     * @return the row label as a string, either derived sequentially from 1, 2, etc. for the first
     *         player's turn or reversed for subsequent turns.
     *         Coded by Chan Ga Wai
     */
    public String getRowNumber(int row){
        return gameController.getCurrentTurn() == 0 ? String.valueOf(row + 1) : String.valueOf(rows - row);
    }

}
