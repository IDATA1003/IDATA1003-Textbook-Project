import java.util.*;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field containing 
 * rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;    

    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical views of the simulation.
    private final List<SimulatorView> views;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        field = new Field(depth, width);
        
        views = new ArrayList<>();
        SimulatorView view = new GridView(depth, width);
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        views.add(view);
        
        view = new GraphView(500, 150, 500);
        view.setColor(Rabbit.class, Color.BLACK);
        view.setColor(Fox.class, Color.RED);
        views.add(view);

        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long 
     * period (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        reportStats();
        for(int n = 1; n <= numSteps && field.isViable(); n++) {
            simulateOneStep();
            delay(30);         // adjust this to change execution speed
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;
        // Use a separate Field to store the starting state of
        // the next step.
        Field nextFieldState = new Field(field.getDepth(), field.getWidth());

        List<Animal> animals = field.getAnimals();
        for (Animal anAnimal : animals) {
            anAnimal.act(field, nextFieldState);
        }
        
        // Replace the old state with the new one.
        field = nextFieldState;

        updateViews();
        reportStats();
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        populate();
        updateViews();
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, location);
                    field.placeAnimal(fox, location);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, location);
                    field.placeAnimal(rabbit, location);
                }
                // else leave the location empty.
            }
        }
    }

    /**
     * Report on the number of each type of animal in the field.
     */
    public void reportStats()
    {
        System.out.print("Step: " + step + " ");
        field.fieldStats();
    }
    
    /**
     * Update all existing views.
     */
    private void updateViews()
    {
        for (SimulatorView view : views) {
            view.showStatus(step, field);
        }
    }
    
    /**
     * Pause for a given time.
     * @param milliseconds The time to pause for, in milliseconds
     */
    private void delay(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e) {
            // ignore
        }
    }
}
