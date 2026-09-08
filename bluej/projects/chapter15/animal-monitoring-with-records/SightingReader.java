import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class to read CSV-style records of animal sighting reports.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class SightingReader
{
    // How many fields are expected.
    private static final int NUMBER_OF_FIELDS = 6;
    // Index values for the fields in each aSighting.
    private static final int SPOTTER = 0,
                             ANIMAL = 1,
                             COUNT = 2,
                             AREA_LETTER = 3,
                             AREA_NUMBER = 4,
                             PERIOD = 5;
    
    /**
     * Create a SightingReader.
     */
    public SightingReader()
    {
    }
    
    /**
     * Read sightings in CSV format from the given file.
     * Return an ArrayList of Sighting objects created from
     * the information in the file.
     * 
     * @param filename The file to be read - should be in CSV format.
     * @return A list of Sightings.
     */
    public ArrayList<Sighting> getSightings(String filename)
    {
        // Create a Sighting from a CSV input line.
        Function<String, Sighting> createSighting = 
            aSighting -> {
                           String[] parts = aSighting.split(",");
                           if(parts.length == NUMBER_OF_FIELDS) {
                               try {
                                   int spotter = Integer.parseInt(parts[SPOTTER].trim());
                                   String animal = parts[ANIMAL].trim();
                                   int count = Integer.parseInt(parts[COUNT].trim());
                                   char areaLetter = parts[AREA_LETTER].trim().charAt(0);
                                   int areaNumber = Integer.parseInt(parts[AREA_NUMBER].trim());
                                   int period = Integer.parseInt(parts[PERIOD].trim());
                                   return new Sighting(animal, spotter, count, new Location(areaLetter, areaNumber), period);
                               }
                               catch(NumberFormatException e) {
                                   System.out.println("Sighting aSighting has a malformed integer: " + aSighting);
                                   return null;
                               }
                           }
                           else {
                               System.out.println("Sighting aSighting has the wrong number of fields: " + aSighting);
                               return null;
                           }
                       };
        ArrayList<Sighting> sightingList;
        try {
            sightingList = Files.lines(Paths.get(filename))
                             .filter(aSighting -> aSighting.length() > 0 && aSighting.charAt(0) != '#')
                             .map(createSighting)
                             .filter(sighting -> sighting != null)
                             .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            sightingList = new ArrayList<>();
        }
        return sightingList;
    }
    
}
