import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Read experimental data from file and return the runs
 * in a list.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 */
public class DataReader
{

    /**
     * Constructor for objects of class DataReader
     */
    public DataReader()
    {
    }

    /**
     * Read experimental runs in CSV format from the given file.
     * Return an ArrayList of ExperimentalRun objects created from
     * the information in the file.
     * 
     * @param filename The file to be read - should be in CSV format.
     * @return A list of ExperimentalRuns.
     */
    public ArrayList<ExperimentalRun> getRuns(String filename)
    {
        // Create a ExperimentalRun from a CSV input line.
        Function<String, ExperimentalRun> createExperimentalRun = 
            line -> {
                int field = 0;
                String[] parts = line.split(",");
                try {
                    ArrayList<Reading> readings = new ArrayList<>();
                    String ID = parts[field].trim();
                    field++;
                    for(; field < parts.length; field += 3) {
                        double value = Double.parseDouble(parts[field]);
                        int hour = Integer.parseInt(parts[field + 1]);
                        int minute = Integer.parseInt(parts[field + 2]);
                        readings.add(new Reading(value, new Time(hour, minute)));
                    }
                    return new ExperimentalRun(ID, readings);
                }
                catch(NumberFormatException e) {
                    System.out.println("The Experimental Run line is malformed at field " + 
                                        field + ": " + line);
                    return null;
                }
            };
        ArrayList<ExperimentalRun> runs;
        try {
            runs = Files.lines(Paths.get(filename))
                        .filter(line -> line.length() > 0 && line.charAt(0) != '#')
                        .map(createExperimentalRun)
                        .filter(experimentalRun -> experimentalRun != null)
                        .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            runs = new ArrayList<>();
        }
        return runs;
    }
}
