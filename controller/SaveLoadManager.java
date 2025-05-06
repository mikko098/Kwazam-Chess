package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;

/**
 * The SaveLoadManager class is responsible for managing the saving and loading
 * of the game state to and from an external file. This includes storing the state
 * of the game board, tracking the move history, turn count, current turn,
 * and details of the pieces on the board.
 *
 * This class provides static methods to save the current game state to a file
 * and to load a previous game state from a file into the provided game state object.
 *
 * It forms a utility class that encapsulates the functionality required for persistent
 * storage and retrieval of the game state. SaveLoadManager can be considered as an
 * implementation of the "Data Access Object" design pattern for file-based storage.
 * Coded by Harrish Panicker
 */
public class SaveLoadManager {

    /**
     * The SaveLoadManager class handles the functionality of saving and loading the game state,
     * and acts as a utility class for persistence in the game. It plays a helper role in managing
     * data for saving and restoring gameplay.
     * Coded by Harrish Panicker
     */
    public static void saveBoard(ArrayList<Piece> boardPieces, int turnCount, int currentTurn, ArrayList<String> movesHistory, String fileName) {
        String directoryPath = "savefiles";
        File directory = new File(directoryPath);

        // Ensure the savefiles directory exists
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        String filePath = directoryPath + "/" + fileName + ".txt";
        System.out.println("Saving file: " + filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Current Board Pieces\n");
            writer.write("---------------------\n");
            for (Piece piece : boardPieces) {
                int row = piece.getRow() + 1; // Rows are 1-indexed in the save file
                char col = (char) (piece.getCol() + 'A'); // Convert column index to letter
                writer.write(piece.getName() + " " + piece.getOwnerName() + " " + col + " " + row + "\n");
            }

            writer.write("\nTurn Count: " + turnCount + "\n");
            writer.write("Current Turn: " + currentTurn + "\n");

            writer.write("\nMove History\n");
            writer.write("------------\n");
            for (String move : movesHistory) {
                writer.write(move + "\n");
            }

            System.out.println("Game state saved successfully as " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save the document: " + e.getMessage());
        }
    }


    /**
     * The SaveLoadManager class handles the functionality of saving and loading the game state,
     * and acts as a utility class for persistence in the game.
     * It plays a helper role in managing data for saving and restoring gameplay.
     * Coded by Harrish Panicker
     */
    public static void loadBoard(String fileName, GameState gameState) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<Piece> loadedPieces = new ArrayList<>();
            ArrayList<String> loadedMoves = new ArrayList<>();
            String line;

            // Skip the first two lines (headers)
            reader.readLine();
            reader.readLine();

            // Read board pieces
            while ((line = reader.readLine()) != null && !line.startsWith("Turn Count:")) {
                String[] parts = line.split(" ");
                if (parts.length != 4) continue; // Ensure the line is valid

                String name = parts[0];
                String ownerName = parts[1];
                int col = parts[2].charAt(0) - 'A'; // Convert column letter to index
                int row = Integer.parseInt(parts[3]) - 1; // Convert 1-indexed row to 0-indexed

                Player owner = gameState.getPlayer(ownerName); // Find the corresponding player
                Piece piece = createPieceByName(name, col, row, owner);
                if (piece != null) {
                    loadedPieces.add(piece);
                }
            }

            // Parse turn count and current turn
            int turnCount = Integer.parseInt(line.split(": ")[1]);
            int currentTurn = Integer.parseInt(reader.readLine().split(": ")[1]);

            // Skip the "Move History" header
            reader.readLine();
            reader.readLine();
            reader.readLine();

            // Read move history
            while ((line = reader.readLine()) != null) {
                loadedMoves.add(line);
            }

            // Update game state
            GameState.pieces.clear();
            GameState.pieces.addAll(loadedPieces);
            gameState.setTurnCount(turnCount);
            gameState.setCurrentTurn(currentTurn);
            gameState.getMovesHistory().clear();
            gameState.getMovesHistory().addAll(loadedMoves);

            // Reset the board
            Tile[][] board = gameState.getBoard();
            for (int i = 0; i < gameState.columns; i++) {
                for (int j = 0; j < gameState.rows; j++) {
                    board[i][j].setPiece(null);
                }
            }
            for (Piece piece : loadedPieces) {
                board[piece.getCol()][piece.getRow()].setPiece(piece);
            }

            System.out.println("Game state loaded successfully from " + fileName + "!");
        } catch (IOException e) {
            System.out.println("Failed to load the document: " + e.getMessage());
        }
    }

    private static Piece createPieceByName(String name, int col, int row, Player owner) {
        return switch (name) {
            case "Ram" -> new Ram(col, row, owner);
            case "Biz" -> new Biz(col, row, owner);
            case "Sau" -> new Sau(col, row, owner);
            case "Xor" -> new Xor(col, row, owner);
            case "Tor" -> new Tor(col, row, owner);
            default -> null; // Unknown piece name
        };
    }
}
