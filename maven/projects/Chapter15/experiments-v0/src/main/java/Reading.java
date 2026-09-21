
/**
 * A single data reading along with the time at which it was made.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Reading
{
    // The value of the reading.
    private final double value;
    // The time of the reading.
    private final Time time;
    
    /**
     * Create a Reading object.
     * @param value The value of the reading.
     * @param time The time the reading was taken.
     */
    public Reading(double value, Time time)
    {
        this.value = value;
        this.time = time;
    }
    
    /**
     * Get the value of the reading.
     * @return The value.
     */
    public double getValue()
    {
        return value;
    }
    
    /**
     * Get the time of the reading.
     * @return The time.
     */
    public Time getTime()
    {
        return time;
    }
    
    /**
     * Get the reading and time.
     * @return The reading and time.
     */
    public String toString()
    {
        return String.format("%.1f @ %s", value, time);
    }
}
