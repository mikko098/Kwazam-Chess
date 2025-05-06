package view;

import java.awt.Graphics2D;

/**
 * The Board class represents the game board, rendering the grid where game actions take place.
 * This class is responsible for drawing the graphical representation of the board.
 * This class work in conjunction with other classes, such as a controller or utility classes,
 * to manage board rendering and interaction during gameplay.
 * Coded by Chan Ga Wai
 */
public class Board {
    final int cols = 5;
    final int rows = 8;
    public static int SquareSize = 70;

    /**
     * Draws the graphical representation of the game board, including the grid and column numbers.
     * This method is responsible for rendering each cell of the grid as well as any additional graphics
     * necessary for the visual representation of the board.
     *
     * @param g2 the Graphics2D object used for drawing on the board.
     *           Coded by Chan Ga Wai
     */
    public void draw(Graphics2D g2) {
        // Draw the grid
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRect(i * SquareSize, j * SquareSize, SquareSize, SquareSize);

                g2.setColor(java.awt.Color.BLACK);
                g2.drawRect(i * SquareSize, j * SquareSize, SquareSize, SquareSize);
            }
        }

        // Draw column numbers at the top
        g2.setColor(java.awt.Color.BLACK);

        g2.setColor(java.awt.Color.WHITE);

    }
}