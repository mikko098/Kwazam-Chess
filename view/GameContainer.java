package view;

import controller.GameController;
import controller.MenuController;
import controller.Mouse;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The GameContainer class serves as the main container for rendering and interacting
 * with the chess game application. It acts as the central hub for integrating the
 * model, view, and controller components of the application.
 *
 * This class extends JPanel for graphical rendering, implements the Observer interface
 * to receive updates from the GameController, and is responsible for managing the layout
 * and interactions of game elements like the board, pieces, UI menus, and history panel.
 *
 * As part of the Observer design pattern, GameContainer observes GameController
 * for state changes, allowing the graphical components to stay updated in real-time.
 * This entire GameContainer.java is coded by Lai Cal Wyn
 */
public class GameContainer extends JPanel implements Observer {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 678;
    public Board board;
    public PieceRenderer renderer;
    private Mouse mouse;
    public MarginText marginText;
    private GameController gameController;
    private JScrollPane movesScrollPane;
    private MovesHistory movesHistory;
    private MenuView menuView;

    /**
     * The GameContainer class serves as the view component in the Kwazam Chess Game,
     * responsible for integrating various components such as the game board, menu,
     * pieces, and the move history into the user interface. It manages interaction
     * with other components like the GameController for handling game logic and the
     * MenuController for managing user interactions with the menu. It observes the
     * game state and updates the view accordingly. This class also acts as a listener
     * for resizing events to dynamically adjust the UI layout.
     *
     * @param gameController the controller responsible for the game's logic and state management
     * @param menuController the controller responsible for managing menu interactions and options
     *                       Coded by Lai Cal Wyn
     */
    public GameContainer(GameController gameController, MenuController menuController) {
        this.gameController = gameController;

        renderer = new PieceRenderer(gameController);
        board = new Board();
        marginText = new MarginText(gameController);

        mouse = new Mouse(gameController);
        addMouseListener(mouse);

        gameController.addObserver(this);

        // Initialize the MenuView (View) with the controller.MenuController as the listener
        menuView = new MenuView(menuController);
        movesHistory = new MovesHistory();

        // Add a ComponentListener to detect resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustSquareSize();
                adjustFontSize();
                adjustTableSize();
                repaint(); // Repaint the board after resizing
            }
        });

        // Set up the JFrame
        JFrame frame = new JFrame("Kwazam Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GameContainer.WIDTH, GameContainer.HEIGHT);
        frame.setLayout(new BorderLayout());

        // Add components to the JFrame
        frame.add(this, BorderLayout.CENTER);

        movesScrollPane = movesHistory.getMoveHistoryScrollPane();

        // Add the JScrollPane to the frame
        frame.add(movesScrollPane, BorderLayout.EAST);

        frame.setJMenuBar(menuView.getMenuBar());

        // Make the frame visible
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    /**
     * Overrides the paintComponent method to render the game interface. This method is
     * responsible for drawing all the visual components of the game, including the board,
     * pieces, margin text, and updating the move history.
     *
     * It interacts with various rendering components, such as the board and renderer,
     * to ensure the UI accurately represents the current game state. Additionally, it
     * handles text rendering around the board and dynamically updates the move history table.
     *
     * @param g the Graphics object used for rendering the visuals on the JPanel.
     *          Coded by Lai Cal Wyn
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        board.draw(g2);
        renderer.draw(g2);
        marginText.draw(g2);
        movesHistory.updateTable();
    }

    /**
     * Updates the user interface and checks the game state.
     *
     * This method is responsible for refreshing the graphical interface, verifying
     * if the game has ended, and handling timer resets if triggered by the game
     * controller. It ensures that the view remains consistent with the latest game
     * state and user interactions.
     *
     * Key functionalities:
     * - Calls the repaint method to refresh the graphical components of the game.
     * - Invokes the checkGameEnd method to determine if the game has concluded and
     *   handles the necessary actions like displaying the winner or restarting the game.
     * - Resets the timer through the MenuView class when the appropriate flag in the
     *   GameController is set.
     *   Coded by Lai Cal Wyn
     */
    public void update() {
        repaint();
        checkGameEnd();
        if (gameController.resetTimer){
            menuView.resetTimer();
            gameController.resetTimer = false;
        }
    }

    /**
     * Adjusts the size of square tiles dynamically based on the dimensions of the game container.
     *
     * This method ensures that the square size adapts to the current container's width and height
     * while maintaining uniformity across all squares. It calculates the maximum possible square
     * size that fits the given dimensions and updates the static `SquareSize` field in the `Board`
     * class accordingly. The calculation considers both the board's row and column configuration
     * to ensure that the squares fit proportionally within the container.
     * Coded by Lai Cal Wyn
     */
    // Dynamically adjust square size based on the container's width
    private void adjustSquareSize() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate the new square size to fit the width and height
        int squareWidth = (panelWidth - Board.SquareSize) / board.cols;
        int squareHeight = (panelHeight - Board.SquareSize) / board.rows;

        // Choose the smaller of the two to ensure squares are uniform
        Board.SquareSize = Math.min(squareWidth, squareHeight);

    }

    /**
     * Dynamically adjusts the font size of the margin text based on the current dimensions of the container.
     *
     * This method ensures that the font size of the margin labels around the game board remains proportional
     * to the container's dimensions. It calculates a scaling factor using the smaller of the width and height
     * of the container and updates the static `fontSize` field in the `MarginText` class accordingly.
     *
     * This adjustment enhances the user interface by maintaining readability and visual balance,
     * especially when the container is resized during gameplay.
     * Coded by Lai Cal Wyn
     */
    private void adjustFontSize() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        // Calculate a scaling factor based on the smaller of the width and height
        int smallerDimension = Math.min(panelWidth, panelHeight);

        // Adjust the font size based on a percentage of the smaller dimension
        MarginText.fontSize = (float) smallerDimension / 40; // Dividing by 20 gives a reasonable scaling factor
    }

    /**
     * Checks if the game has reached its end state and handles the necessary actions accordingly.
     *
     * This method interacts with the GameController to determine if the game has concluded, retrieves
     * the winning player (if applicable), and displays an appropriate dialog to notify the user of the
     * game's outcome. It also provides an option to restart the game or exit the application based on
     * the user's input.
     *
     * Key functionalities:
     * - Verifies the game state using the GameController.
     * - Displays a dialog box indicating the game's conclusion and the winner.
     * - Offers the user the choice to restart the game or exit the application.
     * - Restarts the game via the GameController if the user opts to do so.
     * - Terminates the application if the user chooses not to restart.
     *
     * This method serves as a critical component in ensuring the game loop is concluded gracefully
     * and provides a seamless user experience through appropriate prompts and transitions.
     * Coded by Lai Cal Wyn
     */
    private void checkGameEnd() {
        // Check if the game has ended
        if (gameController.isGameEnded()) {
            String winner = gameController.getWinner(); // Get the winner
            JOptionPane.showMessageDialog(
                    this,
                    "The game has ended! Winner: " + winner,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Optionally, restart or close the game
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to restart the game?",
                    "Restart Game",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                gameController.restartGame();
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Dynamically adjusts the size of the moves history table based on the current dimensions of the game container.
     *
     * This method ensures that the moves history scroll pane maintains its layout balance within the user interface
     * by dynamically updating its preferred size. It calculates the width as half of the current container's width
     * and sets the height to match the full height of the container. This adjustment is crucial for achieving a
     * responsive and user-friendly layout that adjusts seamlessly during container resizing events.
     *
     * This method plays a role in managing the user interface of the GameContainer class, enhancing the overall
     * usability by ensuring the moves list is displayed proportionally and remains accessible across different
     * screen sizes and aspect ratios.
     * Coded by Lai Cal Wyn
     */
    private void adjustTableSize(){
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        movesScrollPane.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
    }

}
