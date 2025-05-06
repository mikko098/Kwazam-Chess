package model;

/**
 * Represents a player in the game. The Player class encapsulates the player's
 * name and acts as a reference for ownership and association throughout the game.
 *
 * This class serves as a foundational construct to associate game pieces, turns,
 * and other elements specific to a single player.
 * Coded by Mishal Mann Nair
 */
public class Player {
    private final String name;

    /**
     * Represents a player in the game. The Player class encapsulates the
     * player's name and acts as a reference for ownership and association
     * throughout the game board and pieces.
     *
     * @param name The name of the player.
     *             Coded by Mishal Mann Nair
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player as a String.
     * Coded by Mishal Mann Nair
     */
    public String getName() {
        return name;
    }
}
