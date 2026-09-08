import java.util.ArrayList;

/**
 * Data gathered as part of an experimental study.
 * The data consists of a list of experimental runs.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class Experiment
{
    private String title;
    private ArrayList<ExperimentalRun> runs;

    /**
     * Constructor for objects of class Experiment
     * @param title The title of the experiment.
     */
    public Experiment(String title)
    {
        this.title = title;
        this.runs = new ArrayList<>();
    }
    
    /**
     * Read data from the given filename.
     * @param filename The file of data.
     */
    public void readData(String filename)
    {
        DataReader reader = new DataReader();
        ArrayList<ExperimentalRun> runs = reader.getRuns(filename);
        if(! runs.isEmpty()) {
            this.runs = runs;
            System.out.println(runs.size() + " experimental runs read.");
        }
        else {
            System.err.println("No data read.");
        }
    }
    
    /** 
     * List the runs.
     */
    public void listRuns()
    {
        System.out.println("Runs for experiment " + title);
        for(ExperimentalRun run : runs) {
            System.out.println(run);
        }
    }
}
