import java.util.List;

/**
 * A single run of an experiment, recording multiple readings.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 */
public record ExperimentalRun(String ID, List<Reading> readings)
{
    /**
     * @param ID The ID of the run.
     * @param readings The list of readings taken on the run.
     */
    public ExperimentalRun(String ID, List<Reading> readings)
    {
        this.ID = ID.trim();
        this.readings = List.copyOf(readings);
    }
    /**
     * Get the experiment's details.
     * @return the ID and the readings.
     */
    public String toString()
    {
        return String.format("Experiment %s, Data: %s",
                             ID, readings);
    }
}
