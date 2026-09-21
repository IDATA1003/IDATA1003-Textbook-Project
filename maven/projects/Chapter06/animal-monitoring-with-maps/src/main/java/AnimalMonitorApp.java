import java.util.ArrayList;

/**
 * A demo application showing how the AnimalMonitor, Sighting and
 * Location classes can be used together to record and query
 * animal sighting data.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class AnimalMonitorApp
{
    /**
     * Build up some sighting records, add them to an AnimalMonitor,
     * and then demonstrate the various ways the recorded data can
     * be queried and reported.
     * @param args Not used.
     */
    public static void main(String[] args)
    {
        AnimalMonitor monitor = new AnimalMonitor();

        // Add some individual sightings.
        monitor.addSighting(new Sighting("Fox", 1, 2, new Location('C', 3), 1));
        monitor.addSighting(new Sighting("Fox", 2, 0, new Location('C', 3), 1));
        monitor.addSighting(new Sighting("Deer", 1, 5, new Location('B', 7), 1));
        monitor.addSighting(new Sighting("Deer", 3, 1, new Location('B', 7), 2));
        monitor.addSighting(new Sighting("Badger", 2, 0, new Location('A', 1), 1));

        System.out.println("All recorded sightings:");
        monitor.printList();

        System.out.println("\nSightings of Fox:");
        monitor.printSightingsOf("Fox");

        System.out.println("\nSightings made by spotter 1:");
        monitor.printSightingsBy(1);

        System.out.println("\nTotal Deer sightings: " + monitor.getCount("Deer"));

        System.out.println("\nSightings of Deer in location B7:");
        ArrayList<Sighting> inLocation = monitor.getSightingsInLocation("Deer", new Location('B', 7));
        for(Sighting aSighting : inLocation) {
            System.out.println(aSighting.getDetails());
        }

        ArrayList<String> animalsToCheck = new ArrayList<>();
        animalsToCheck.add("Fox");
        animalsToCheck.add("Deer");
        animalsToCheck.add("Badger");
        System.out.println("\nChecking for endangered animals (threshold = 1):");
        monitor.printEndangered(animalsToCheck, 1);

        System.out.println("\nRemoving sightings with a count of zero.");
        monitor.removeZeroCounts();

        System.out.println("\nAll recorded sightings after cleanup:");
        monitor.printList();
    }
}
