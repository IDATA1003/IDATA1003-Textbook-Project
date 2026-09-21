
/**
 * A single reading within an experimental run.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 * @param value The value of the reading.
 * @param time The time the reading was taken.
 */
public record Reading(double value, Time time)
{
    /**
     * Get the reading and time.
     * @return The reading and time.
     */
    public String toString()
    {
        return String.format("%.1f @ %s", value, time);
    }
}
