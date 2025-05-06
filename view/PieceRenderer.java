package view;

import controller.GameController;
import model.Piece;
import model.intPair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The PieceRenderer class is responsible for rendering game pieces and their valid moves
 * on the board for a given game state. It handles loading piece images, drawing them
 * on the board, and visually indicating valid moves for the current turn.
 *
 * This class acts as part of the graphical representation layer of the application,
 * translating game logic into visual elements for the user.
 * This entire PieceRenderer.java is coded by Mishal Mann Nair
 */
public class PieceRenderer {

    /**
     * Manages the game state and controls the interaction between user input,
     * game logic, and rendering of the game board.
     * Acts as the controller component in the Model-View-Controller (MVC) architecture.
     * Coded by Mishal Mann Nair
     */
    private final GameController gameController;

    /**
     * A mapping of piece identifiers to their corresponding images.
     * This map is used to store and retrieve graphical representations of game pieces
     * for rendering purposes within the PieceRenderer class.
     * Coded by Mishal Mann Nair
     */
    private final Map<String, BufferedImage> pieceImages;

    /**
     * The PieceRenderer class is responsible for rendering game pieces on the board.
     * It loads and maintains visual representations of the pieces and handles their drawing and positioning during gameplay.
     * Part of the Game View layer, it collaborates with the GameController to correctly display the state of the game.
     *
     * @param gameController The GameController instance used to access game state and logic.
     *                       Coded by Mishal Mann Nair
     */
    public PieceRenderer(GameController gameController) {
        this.gameController = gameController;
        this.pieceImages = loadPieceImages();
    }

    /**
     * This method loads and returns a mapping of piece types and player identities to their corresponding images.
     * The images are loaded from the "images" directory using the naming convention "Type_Player.png",
     * where "Type" represents the piece type (e.g., "Biz", "Tor", etc.) and "Player" identifies the player ("P1" or "P2").
     *
     * @return A map where the keys are strings combining piece types and player identifiers (e.g., "Biz_P1"),
     *         and the values are BufferedImage objects representing the loaded images.
     *         Coded by Mishal Mann Nair
     */
    private Map<String, BufferedImage> loadPieceImages() {
        Map<String, BufferedImage> images = new HashMap<>();
        String[] pieceTypes = {"Biz", "Tor", "Xor", "Sau", "Ram"};
        String[] playerNames = {"P1", "P2"};

        for (String type : pieceTypes) {
            for (String player : playerNames) {
                String key = type + "_" + player;
                images.put(key, loadImage( key + ".png"));
            }
        }
        return images;
    }

    /**
     * Loads an image from the specified path within the "images" directory
     * and returns it as a BufferedImage object.
     * If the image cannot be found or fails to load, it prints an error message
     * and returns null.
     *
     * @param imagePath The name and relative path of the image file within the "images" directory.
     * @return The loaded BufferedImage object if successful, or null if the image could not be loaded.
     * Coded by Mishal Mann Nair
     */
    private BufferedImage loadImage(String imagePath) {
        try {
            var resourceStream = getClass().getResourceAsStream("/images/" + imagePath);
            if (resourceStream == null) {
                System.err.println("Image not found: " + imagePath);
                return null;
            }
            return ImageIO.read(resourceStream);
        } catch (IOException e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Renders all game pieces on the board and adjusts their positioning and orientation
     * according to the current turn of the game.
     *
     * @param g2 The Graphics2D object used for rendering visuals.
     *           Coded by Mishal Mann Nair
     */
    public void draw(Graphics2D g2) {
        drawValidMoves(g2);
        boolean isEvenTurn = gameController.getCurrentTurn() % 2 == 0;

        for (Piece piece : gameController.getPieces()) {
            int x = isEvenTurn ? piece.getX() : 4 * Board.SquareSize - piece.getX();
            int y = isEvenTurn ? piece.getY() : 7 * Board.SquareSize - piece.getY();
            String imageKey = piece.getName() + "_" + piece.getOwnerName();

            BufferedImage pieceImage = pieceImages.get(imageKey);
            if (pieceImage != null) {
                if (isEvenTurn) {
                    g2.drawImage(pieceImage, x, y, Board.SquareSize, Board.SquareSize, null);
                }
                else {
                    g2.drawImage(pieceImage, x + Board.SquareSize, y + Board.SquareSize, -Board.SquareSize, -Board.SquareSize, null);
                }
            }
        }
    }

    /**
     * Renders the valid moves for the current game piece onto the game board.
     * This method retrieves the current piece and its possible moves, determining
     * the valid positions where the piece can move. It visually highlights valid
     * moves on the board by drawing colored squares at the appropriate locations.
     *
     * @param g2 The Graphics2D object used to draw the visual representations
     *           of the valid move positions on the game board.
     *           Coded by Mishal Mann Nair
     */
    private void drawValidMoves(Graphics2D g2) {
        Piece currentPiece = gameController.getCurrentPiece();

        if (currentPiece != null) {
            var moves = gameController.getMoves();
            boolean isEvenTurn = gameController.getCurrentTurn() % 2 == 0;

            if (moves == null) {
                return;
            }

            for (intPair move : moves) {
                int x = isEvenTurn ? move.getX() * Board.SquareSize : (4 - move.getX()) * Board.SquareSize;
                int y = isEvenTurn ? move.getY() * Board.SquareSize : (7 - move.getY()) * Board.SquareSize;

                Piece targetPiece = gameController.getPiece(move.getX(), move.getY());
                if (targetPiece == null || !targetPiece.getOwnerName().equals(currentPiece.getOwnerName())) {
                    g2.setColor(Color.PINK);
                    g2.fillRect(x, y, Board.SquareSize, Board.SquareSize);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, Board.SquareSize, Board.SquareSize);
                }
            }
        }
    }
}