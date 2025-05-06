package view;

import controller.GameController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * The MovesHistory class is responsible for maintaining and displaying a tabular record
 * of moves made during a two-player game. It updates the table dynamically based on the
 * game's move history and provides a UI component for embedding within a larger interface.
 *
 * This class plays a key role in providing a visual representation of the gameplay progress
 * by fetching the latest moves from the game controller and displaying turn-based data
 * for each player.
 *
 * It includes functionality to format and update the table as well as the ability to
 * customize its appearance based on the intended UI design.
 * Coded by Harrish Panicker
 */
public class MovesHistory {
    private JTable moveHistory;
    private JScrollPane moveHistoryScrollPane;
    private static DefaultTableModel tableModel;

    /**
     * The MovesHistory class is responsible for managing and displaying the history of player moves during a game.
     * This class creates a table to show a turn-based log of the moves made by Player 1 (P1) and Player 2 (P2).
     * It dynamically updates the table as moves are registered, ensuring a clear and organized view of the gameplay history.
     *
     * This class functions as part of the game's graphical user interface (GUI), aiding in tracking the game's progress.
     * It is particularly useful for debugging, reviewing game flow, or providing an enhanced player experience.
     *
     * Part of View in the context of an MVC (Model-View-Controller) architecture.
     * Coded by Harrish Panicker
     */
    public MovesHistory() {
        // Define column names
        String[] columnNames = {"Turn", "P1 Move", "P2 Move"};

        // Initialize table model
        tableModel = new DefaultTableModel(columnNames, 0);
        moveHistory = new JTable(tableModel);
        moveHistory.setBackground(Color.BLACK);
        moveHistory.setForeground(Color.WHITE);
        moveHistory.setGridColor(Color.WHITE);

        // Populate the table initially
        updateTable();
    }

    /**
     * Updates the moves history table by clearing its current content and repopulating it
     * with the moves retrieved from the GameController's `getMovesHistory` method.
     *
     * For every two moves in the moves history list, a single row is added to the table
     * representing one turn, with Player 1's move and Player 2's move shown in adjacent columns.
     * The turn counter column increments with each turn.
     *
     * If the total number of moves is odd, the last move (Player 1's move) is added to the table
     * as a separate row, leaving Player 2's column blank.
     *
     * This method ensures that the table accurately reflects the chronological order of the
     * moves in the game while maintaining a clear and formatted structure for display.
     * Coded by Harrish Panicker
     */
    // Fetch movesHistory from GameState and populate the table
    public void updateTable() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Fetch the movesHistory from GameController
        ArrayList<String> moves = GameController.getMovesHistory();

        // Populate the table with turn-based moves
        int turnCounter = 1;
        String p1Move = "";
        String p2Move = "";
        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                p1Move = moves.get(i); // P1's move
            } else {
                p2Move = moves.get(i); // P2's move
                tableModel.addRow(new Object[]{turnCounter, p1Move, p2Move});
                turnCounter++;
                p1Move = ""; // Reset P1 move
                p2Move = ""; // Reset P2 move
            }
        }

        // If there's an odd move (P1's move without P2), add it as the last row
        if (!p1Move.isEmpty()) {
            tableModel.addRow(new Object[]{turnCounter, p1Move, ""});
        }
    }

    /**
     * Retrieves the move history table, which displays a log of moves made during the game.
     * The table organizes the moves of Player 1 (P1) and Player 2 (P2) turn by turn,
     * providing a chronological and structured representation of game progress.
     *
     * This method is mainly used to access the JTable component for integration within
     * the graphical user interface (GUI), enabling visualization and interaction with
     * the game's move history.
     *
     * @return the JTable containing the move history.
     * Coded by Harrish Panicker
     */
    public JTable getMoveHistoryTable() {
        return moveHistory;
    }

    /**
     * Retrieves a JScrollPane that wraps around the move history table, providing scrollable access
     * to the game's move log. The JScrollPane enhances usability by allowing users to efficiently
     * navigate through the table if it exceeds the visible area.
     *
     * The scroll pane is customized with a black background, a black border, and a defined preferred
     * size suited for integration into the game's graphical user interface (GUI). The move history
     * table contained within this JScrollPane organizes and displays the chronological order of
     * player moves during the game.
     *
     * @return the JScrollPane containing the move history table.
     * Coded by Harrish Panicker
     */
    public JScrollPane getMoveHistoryScrollPane() {
        // Create a JScrollPane for the MovesHistory table
        moveHistoryScrollPane = new JScrollPane(getMoveHistoryTable());

        // Set the colors for the JScrollPane
        moveHistoryScrollPane.setBackground(Color.BLACK);
        moveHistoryScrollPane.getViewport().setBackground(Color.BLACK); // Set the table's background through the viewport
        moveHistoryScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set the border to black

        // Set the preferred size of the JScrollPane to make it smaller
        moveHistoryScrollPane.setPreferredSize(new Dimension(200, GameContainer.HEIGHT));

        return moveHistoryScrollPane;
    }
}
