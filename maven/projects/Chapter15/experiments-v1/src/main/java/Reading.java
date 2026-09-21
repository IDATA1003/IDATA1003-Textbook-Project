
/**
 * A single reading within an experimental run.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 * @param value The value of the reading.
 * @param time The time the reading was taken.
 */
public record Reading(double value, Time time)
{
}
