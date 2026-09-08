import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.1
 */
public class AnimalMonitor
{
    private ArrayList<Sighting> sightingList;
    
    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        this.sightingList = new ArrayList<>();
    }
        
    /**
     * Add the sightings from the given file.
     * @param filename A CSV file of recorded sightings.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        sightingList.addAll(reader.getSightings(filename));
    }
    
    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        sightingList.forEach(aSighting -> System.out.println(aSighting.getDetails()));
    }
    
    /**
     * Print details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        sightingList.stream()
                    .filter(aSighting -> animal.equals(aSighting.getAnimal()))
                    .forEach(aSighting -> System.out.println(aSighting.getDetails()));        
    }
    
    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter)
    {
        sightingList.stream()
                    .filter(aSighting -> aSighting.getSpotter() == spotter)
                    .map(aSighting -> aSighting.getDetails())
                    .forEach(details -> System.out.println(details));        
    }
    
    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        return sightingList.stream()
                           .filter(aSighting -> animal.equals(aSighting.getAnimal()))
                           .map(aSighting -> aSighting.getCount())
                           .reduce(0, (runningSum, count) -> runningSum + count);
    }
}
