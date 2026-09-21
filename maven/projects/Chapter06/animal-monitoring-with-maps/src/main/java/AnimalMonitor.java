import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.2
 */
public class AnimalMonitor 
{
    // Records of all the sightings of each type of animal.
    private HashMap<String, ArrayList<Sighting>> animalSightings;

    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        animalSightings = new HashMap<>();
    }

    /**
     * Add the sightings from the given file.
     * @param filename A CSV file of sighting records.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        ArrayList<Sighting> updates = reader.getSightings(filename);
        for(Sighting aSighting : updates) {
            addSighting(aSighting);
        }
    }

    /**
     * Add a single sighting to our records.
     * @param aSighting The sighting object to be added.
     */
    public void addSighting(Sighting aSighting)
    {
        String animal = aSighting.getAnimal();
        ArrayList<Sighting> sightingList = animalSightings.get(animal);
        if(sightingList == null) {
            sightingList = new ArrayList<>();
            animalSightings.put(animal, sightingList);
        }
        sightingList.add(aSighting);
    }

    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        for(String animal : animalSightings.keySet()) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                System.out.println(aSighting.getDetails());
            }
        }
    }

    /**
     * Print the details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        if(animalSightings.containsKey(animal)) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                System.out.println(aSighting.getDetails());
            }
        }
    }

    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter)
    {
        for(String animal : animalSightings.keySet()) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                if(aSighting.getSpotter() == spotter) {
                    System.out.println(aSighting.getDetails());
                }
            }        
        }
    }

    /**
     * Print a list of the types of animal considered to be endangered.
     * @param animalNames A list of animals names.
     * @param dangerThreshold Counts less-than or equal-to to this level
     *                        are considered to be dangerous.
     */
    public void printEndangered(ArrayList<String> animalNames, 
    int dangerThreshold)
    {
        for(String animal : animalNames) {
            if(getCount(animal) <= dangerThreshold) {
                System.out.println(animal + " is endangered.");
            }
        }
    }

    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        int total = 0;
        if(animalSightings.containsKey(animal)) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                total = total + aSighting.getCount();
            }
        }
        return total;
    }

    /**
     * Remove from the sightings list all of those aSightings with
     * a count of zero.
     */
    public void removeZeroCounts()
    {
        for(String animal : animalSightings.keySet()) {
            ArrayList<Sighting> sightingList = animalSightings.get(animal);
            sightingList.removeIf(aSighting -> aSighting.getCount() == 0);
        }
    }

    /**
     * Return a list of all sightings of the given type of animal
     * in a particular grid location.
     * @param animal The type of animal.
     * @param loc The location of interest.
     * @return A list of sightings.
     */
    public ArrayList<Sighting> getSightingsInLocation(String animal, Location loc)
    {
        ArrayList<Sighting> sightingList = new ArrayList<>();
        if(animalSightings.containsKey(animal)) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                if(aSighting.getLocation().equals(loc)) {
                    sightingList.add(aSighting);
                }
            }
        }
        return sightingList;
    }

    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    public ArrayList<Sighting> getSightingsOf(String animal)
    {
        return animalSightings.getOrDefault(animal, new ArrayList<>());
    }
}
