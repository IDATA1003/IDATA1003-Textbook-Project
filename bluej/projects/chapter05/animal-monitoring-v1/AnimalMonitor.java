import java.util.ArrayList;
import java.util.Iterator;

/**
 * Monitor counts of different types of animal. Sightings are recorded 
 * by spotters. This version uses imperative constructs for processing the data.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class AnimalMonitor 
{
    // Records of all the sightings of animals.
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
        for(Sighting aSighting : sightingList) {
            System.out.println(aSighting.getDetails());
        }
    }
    
    /**
     * Print the details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        for(Sighting aSighting : sightingList) {
            if(animal.equals(aSighting.getAnimal())) {
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
        for(Sighting aSighting : sightingList) {
            if(aSighting.getSpotter() == spotter) {
                System.out.println(aSighting.getDetails());
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
        for(Sighting aSighting : sightingList) {
            if(animal.equals(aSighting.getAnimal())) {
                total = total + aSighting.getCount();
            }
        }
        return total;
    }
    
    /**
     * Remove from the sightings all of those records with
     * a count of zero.
     */
    public void removeZeroCounts()
    {
        Iterator<Sighting> it = sightingList.iterator();
        while(it.hasNext()) {
            Sighting aSighting = it.next();
            if(aSighting.getCount() == 0) {
                it.remove();
            }
        }
    }
    
    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     * @param animal The type of animal.
     * @param area The ID of the area.
     * @return A list of sightings.
     */
    public ArrayList<Sighting> getSightingsInArea(String animal, int area)
    {
        ArrayList<Sighting> inTheArea = new ArrayList<>();
        for(Sighting aSighting : sightingList) {
            if(animal.equals(aSighting.getAnimal())) {
                if(aSighting.getArea() == area) {
                    inTheArea.add(aSighting);
                }
            }
        }
        return inTheArea;
    }
        
    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    public ArrayList<Sighting> getSightingsOf(String animal)
    {
        ArrayList<Sighting> filtered = new ArrayList<>();
        for(Sighting aSighting : sightingList) {
            if(animal.equals(aSighting.getAnimal())) {
                filtered.add(aSighting);
            }
        }
        return filtered;
    }
}
