/**
 * Details of a sighting of a type of animal by an individual spotter.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 * 
 * @param animal The animal spotted.
 * @param spotter The ID of the spotter.
 * @param count How many were seen (>= 0).
 * @param location The location in which they were seen.
 * @param period The reporting period.
 */
public record Sighting(String animal, int spotter, int count, 
                       Location location, int period)
{
    /**
     * Return a string containing details of the animal, the number seen,
     * where they were seen, who spotted them and when.
     * @return A string giving details of the sighting.
     */
    public String toString() 
    {
        return animal + 
               ", count = " + count + 
               ", location = " + location + 
               ", spotter = " + spotter + 
               ", period = " + period;
    }
}
