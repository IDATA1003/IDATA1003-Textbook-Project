import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 */
public class AnimalMonitor 
{
    // Records of all the sightings of each type of animal.
    private Map<String, List<Sighting>> animalSightings;
    // Grid of where all the sightings have occurred.
    private Map<Location, List<Sighting>> sightingsGrid;

    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        animalSightings = new HashMap<>();
        sightingsGrid = new HashMap<>();
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
        // Store the sighting in the animals map.
        String animal = aSighting.animal();
        List<Sighting> sightingList = animalSightings.get(animal);
        if(sightingList == null) {
            sightingList = new ArrayList<>();
            animalSightings.put(animal, sightingList);
        }
        sightingList.add(aSighting);

        // Store the sighting in the location map.
        Location loc = aSighting.location();
        sightingList = sightingsGrid.get(loc);
        if(sightingList == null) {
            sightingList = new ArrayList<>();
            sightingsGrid.put(loc, sightingList);
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
                System.out.println(aSighting);
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
                System.out.println(aSighting);
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
                if(aSighting.spotter() == spotter) {
                    System.out.println(aSighting);
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
    public void printEndangered(ArrayList<String> animalNames, int dangerThreshold)
    {
        for(String animal : animalNames) {
            if(count(animal) <= dangerThreshold) {
                System.out.println(animal + " is endangered.");
            }
        }
    }

    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int count(String animal)
    {
        int total = 0;
        if(animalSightings.containsKey(animal)) {
            for(Sighting aSighting : animalSightings.get(animal)) {
                total = total + aSighting.count();
            }
        }
        return total;
    }

    /**
     * Remove from the sightings all of those with a count of zero.
     */
    public void removeZeroCounts()
    {
        for(String animal : animalSightings.keySet()) {
            Iterator<Sighting> it = animalSightings.get(animal).iterator();
            while(it.hasNext()) {
                Sighting aSighting = it.next();
                if(aSighting.count() == 0) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Return a list of all sightings of the given type of animal
     * in a particular grid location.
     * @param animal The type of animal.
     * @param loc The location of interest.
     * @return A list of sightings.
     */
    public List<Sighting> getSightingsInLocation(String animal, Location loc)
    {
        List<Sighting> sightingList = new ArrayList<>();
        if(sightingsGrid.containsKey(loc)) {
            for(Sighting aSighting : sightingsGrid.get(loc)) {
                if(aSighting.animal().equals(animal)) {
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
        ArrayList<Sighting> filtered = new ArrayList<>();
        if(animalSightings.containsKey(animal)) {
            filtered.addAll(animalSightings.get(animal));
        }
        return filtered;
    }

    /**
     * Count the number of sightings of the given animal within a given area.
     * @param animal The animal to find.
     * @param topLeft The top-left of a rectangular area.
     * @param bottomRight The bottom-right of a rectangular area.
     * @return The total number of matching sightings. 
     */
    public int countSightingsInArea(String animal, Location topLeft, 
                                    Location bottomRight)
    {
        int total = 0;
        for(Location loc : sightingsGrid.keySet()) {
            if(loc.row() >= topLeft.row() && loc.row() <= bottomRight.row() &&
               loc.column() >= topLeft.column() && 
               loc.column() <= bottomRight.column()) {
                for(Sighting sighting : sightingsGrid.get(loc)) {
                    if(animal.equals(sighting.animal())) {
                        total += sighting.count();
                    }
                }
            }
        }
        return total;
    }
}
