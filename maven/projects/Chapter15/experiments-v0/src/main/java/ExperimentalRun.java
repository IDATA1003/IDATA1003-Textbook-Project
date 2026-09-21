import java.util.List;

/**
 * A single run of an experiment, recording multiple readings.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class ExperimentalRun
{
    // The ID of the run
    private final String ID;
    // The list of readings.
    private final List<Reading> readings;
    
    /**
     * @param ID The ID of the run.
     * @param readings The list of readings taken on the run.
     */
    public ExperimentalRun(String ID, List<Reading> readings)
    {
        this.ID = ID;
        this.readings = readings;
    }
    
    /**
     * Get the ID.
     * @return The ID.
     */
    public String getID()
    {
        return ID;
    }
    
    /**
     * Get the readings.
     * @return The readings.
     */
    public List<Reading> getReadings()
    {
        return readings;
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
