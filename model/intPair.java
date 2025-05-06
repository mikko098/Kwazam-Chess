package model;

/**
 * Represents a pair of integers, commonly used for representing coordinates or paired numerical data.
 * This class facilitates structured management of two related integer values, such as positions on a grid.
 *
 * This class is often utilized in scenarios involving board games, navigation, or algorithms requiring
 * paired integer values.
 * Coded by Lai Cal Wyn
 *
 */
public class intPair {
    int x;
    int y;

    /**
     * Represents a pair of integers, typically used to denote a set of coordinates
     * (x, y) or as a utility for managing paired numerical data.
     *
     * @param x The x-coordinate or first integer in the pair.
     * @param y The y-coordinate or second integer in the pair.
     *          Coded by Lai Cal Wyn
     */
    public intPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the x-coordinate or the first integer value of the pair.
     *
     * @return The x-coordinate or the first integer in the pair.
     * Coded by Lai Cal Wyn
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate or the second integer value of the pair.
     *
     * @return The y-coordinate or the second integer in the pair.
     * Coded by Lai Cal Wyn
     */
    public int getY() {
        return y;
    }
}
