/**
 * Model a location in a city.
 * @param x The x coordinate. Must be positive.
 * @param y The y coordinate. Must be positive.
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 */
public record Location(int x, int y)
{
    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be positive.
     * @param y The y coordinate. Must be positive.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                        "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                        "Negative y-coordinate: " + y);
        }
        this.x = x;
        this.y = y;
    }
    
    /**
     * Generate the next location to visit in order to
     * reach the destination.
     * @param destination Where we want to get to.
     * @return A location in a direct line from this to
     *         destination.
     */
    public Location nextLocation(Location destination)
    {
        int destX = destination.x();
        int destY = destination.y();
        int offsetX = x < destX ? 1 : x > destX ? -1 : 0;
        int offsetY = y < destY ? 1 : y > destY ? -1 : 0;
        if(offsetX != 0 || offsetY != 0) {
            return new Location(x + offsetX, y + offsetY);
        }
        else {
            return destination;
        }
    }

    /**
     * Determine the number of movements required to get
     * from here to the destination.
     * @param destination The required destination.
     * @return The number of movement steps.
     */
    public int distance(Location destination)
    {
        int xDist = Math.abs(destination.x() - x);
        int yDist = Math.abs(destination.y() - y);
        return Math.max(xDist, yDist);
    }

    /**
     * @return A representation of the location.
     */
    public String toString()
    {
        return "location " + x + "," + y;
    }

}
