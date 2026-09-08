/**
 * A voltage level.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 * @param voltage A voltage level.
 */
public record Voltage(double voltage)
{
    /**
     * Get a String version of the value.
     * @return A String version of the value.
     */
    public String toString()
    {
        return String.format("%.1f", voltage);
    }
}