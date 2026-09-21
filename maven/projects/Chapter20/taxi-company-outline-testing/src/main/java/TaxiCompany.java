import java.util.ArrayList;
import java.util.List;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class TaxiCompany  
{
    private final List<Vehicle> vehicles;

    /**
     * Constructor for objects of class TaxiCompany
     */
    public TaxiCompany()
    {
        vehicles = new ArrayList<>();
    }

    /**
     * Request a pickup for the given passenger.
     * @param passenger The passenger requesting a pickup.
     * @return Whether a free vehicle is available.
     */
    public boolean requestPickup(Passenger passenger)
    {
        Vehicle vehicle = scheduleVehicle();
        if(vehicle != null) {
            vehicle.setPickupLocation(passenger.getPickupLocation());
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * A vehicle has arrived at a pickup point.
     * @param vehicle The vehicle at the pickup point.
     */
    public void arrivedAtPickup(Vehicle vehicle)
    {
    }
    
    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Vehicle vehicle,
                                     Passenger passenger)
    {
    }
    
    /**
     * Find a free vehicle, if any.
     * @return A free vehicle, or null if there is none.
     */
    private Vehicle scheduleVehicle()
    {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isFree()) {
                return vehicle;
            }
        }
        return null;
    }
}
