import java.util.*;

/**
 * Represent a rectangular grid of field positions.
 * Each position is able to store a single animal/object.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Field
{
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();
    
    // The dimensions of the field.
    private final int depth, width;
    // Animals mapped by location.
    private final Map<Location, Object> field = new HashMap<>();
    // The two types of animal.
    private final List<Rabbit> rabbits = new ArrayList<>();
    private final List<Fox> foxes = new ArrayList<>();

    /**
     * Represent a field of the given dimensions.
     * @param depth The depth of the field.
     * @param width The width of the field.
     */
    public Field(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
    }

    /**
     * Place a rabbit at the given location.
     * If there is already an animal at the location it will be lost.
     * @param rabbit The rabbit to be placed.
     * @param location Where to place the rabbit.
     */
    public void placeRabbit(Rabbit rabbit, Location location)
    {
        assert location != null;
        Object other = field.get(location);
        if(other instanceof Rabbit anotherRabbit) {
            rabbits.remove(anotherRabbit);
        }
        else if(other instanceof Fox aFox) {
            foxes.remove(aFox);
        }
        field.put(location, rabbit);
        rabbits.add(rabbit);
    }

    /**
     * Place a fox at the given location.
     * If there is already an animal at the location it will be lost.
     * @param fox The fox to be placed.
     * @param location Where to place the fox.
     */
    public void placeFox(Fox fox, Location location)
    {
        assert location != null;
        Object other = field.get(location);
        if(other instanceof Rabbit aRabbit) {
            rabbits.remove(aRabbit);
        }
        else if(other instanceof Fox anotherFox) {
            foxes.remove(anotherFox);
        }
        field.put(location, fox);
        foxes.add(fox);
    }

    /**
     * Return the animal at the given location, if any.
     * @param location Where in the field.
     * @return The animal at the given location, or null if there is none.
     */
    public Object getObjectAt(Location location)
    {
        return field.get(location);
    }

    /**
     * Get a shuffled list of the free adjacent locations.
     * @param location Get locations adjacent to this.
     * @return A list of free adjacent locations.
     */
    public List<Location> getFreeAdjacentLocations(Location location)
    {
        List<Location> free = new LinkedList<>();
        List<Location> adjacent = getAdjacentLocations(location);
        for(Location nextLocation : adjacent) {
            Object animal = field.get(nextLocation);
            if(animal == null) {
                free.add(nextLocation);
            }
            else {
                if(animal instanceof Rabbit rabbit && !rabbit.isAlive()) {
                    free.add(nextLocation);
                }
                else if(animal instanceof Fox fox && !fox.isAlive()) {
                    free.add(nextLocation);
                }
            }
        }
        return free;
    }

    /**
     * Return a shuffled list of locations adjacent to the given one.
     * The list will not include the location itself.
     * All locations will lie within the grid.
     * @param location The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Location> getAdjacentLocations(Location location)
    {
        // The list of locations to be returned.
        List<Location> locations = new ArrayList<>();
        if(location != null) {
            int row = location.row();
            int col = location.col();
            for(int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }
            
            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Print out the number of foxes and rabbits in the field.
     */
    public void fieldStats()
    {
        int numFoxes = 0, numRabbits = 0;
        for(Object animal : field.values()) {
            if(animal instanceof Fox fox) {
                if(fox.isAlive()) {
                    numFoxes++;
                }
            }
            else if(animal instanceof Rabbit rabbit) {
                if(rabbit.isAlive()) {
                    numRabbits++;
                }
            }
        }
        System.out.println("Rabbits: " + numRabbits +
                            " Foxes: " + numFoxes);
    }

    /**
     * Empty the field.
     */
    public void clear()
    {
        field.clear();
    }

    /**
     * Return whether there is at least one rabbit and one fox in the field.
     * @return true if there is at least one rabbit and one fox in the field.
     */
    public boolean isViable()
    {
        return ! (rabbits.isEmpty() || foxes.isEmpty());
    }

    /**
     * Return the depth of the field.
     * @return The depth of the field.
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * Return the width of the field.
     * @return The width of the field.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Return a list of the rabbits that are alive.
     * @return The list of rabbits that are alive.
     */
    public List<Rabbit> getRabbits() 
    {
        return rabbits.stream().filter(Rabbit::isAlive).toList();
    }

    /**
     * Return a list of the foxes that are alive.
     * @return The list of foxes that are alive.
     */
    public List<Fox> getFoxes() 
    {
        return foxes.stream().filter(Fox::isAlive).toList();
    }
}
