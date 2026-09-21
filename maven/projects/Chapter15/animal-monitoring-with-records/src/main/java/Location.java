

import java.util.List;

/**
 * A grid location in the form of a row/column pair;
 * for instance C3, R101, etc.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public record Location(char row, int column)
{
    /**
     * Get the neighbors of this location.
     * @return The list of neighbours.
     */
    public List<Location> getNeighbors()
    {
        return List.of(
            new Location((char) (row-1), column),
            new Location((char) (row-1), column-1),
            new Location((char) (row-1), column+1),
            new Location((char) (row+1), column),
            new Location((char) (row+1), column-1),
            new Location((char) (row+1), column+1),
            new Location(row, column-1),
            new Location(row, column+1)
        );
    }
    
    /**
     * Get the Manhattan distance of this Location from another.
     * @param other Another location.
     * @return The Manhattan distance between the two locations.
     */
    public int distance(Location other)
    {
        return Math.abs(row - other.row) + Math.abs(column - other.column);
    }
}
