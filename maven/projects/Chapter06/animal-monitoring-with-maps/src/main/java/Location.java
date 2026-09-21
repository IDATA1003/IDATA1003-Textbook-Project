import java.util.Objects;

/**
 * A grid location in the form of a letter/number pair;
 * for instance C3, R101, etc.
 *
 * @author (your name)
 * @version 7.0
 */
public class Location
{
    // The grid letter.
    private char letter;
    // The grid number.
    private int number;

    /**
     * Create a Location object.
     * @param letter The grid letter.
     * @param number The grid number.
     */
    public Location(char letter, int number)
    {
        this.letter = letter;
        this.number = number;
    }

    /**
     * Return the grid letter.
     * @return the grid letter.
     */
    public char getLetter()
    {
        return letter;
    }
    
    /**
     * Return the grid number.
     * @return the grid number.
     */
    public int getNumber()
    {
        return number;
    }
    
    /**
     * Return a composite of letter number.
     * @return "C1", "R101", etc.
     */
    public String toString()
    {
        return "" + letter + number;
    }
    
    /**
     * Generate a unique hash code for the grid location.
     * @return a hash code.
     */
    public int hashCode()
    {
        return Objects.hash(letter, number);
    }
    
    /**
     * Determine whether this object is equal to other.
     * @param other The other object to compare against.
     * @return true if the objects represent the same location,
     *         false otherwise.
     */
    public boolean equals(Object other)
    {
        if(other == this) {
            return true;
        }
        else if(other == null) {
            return false;
        }
        else if(this.getClass() != other.getClass()) {
            return false;
        }
        else {
            Location otherLocation = (Location) other;
            return letter == otherLocation.letter &&
                   number == otherLocation.number;
        }

    }
}
