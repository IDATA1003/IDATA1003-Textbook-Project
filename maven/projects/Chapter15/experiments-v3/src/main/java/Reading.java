

/**
 * A single reading within an experimental run.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 * @param value The value of the reading.
 * @param time The time the reading was taken.
 */
public record Reading(Voltage theVoltage, Time time)
{
    /**
     * Get the reading and time.
     * @return The reading and time.
     */
    public String toString()
    {
        return String.format("%s @ %s", theVoltage, time);
    }
}
