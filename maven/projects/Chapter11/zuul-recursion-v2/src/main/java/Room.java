import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.1
 */

public class Room 
{
    // The room's name.
    private final String name;
    // The room's description.
    private final String description;
    // Exits to connected rooms.
    private final HashMap<String, Room> exits;

    /**
     * Create a room described "description".
     * @param name The room's name.
     * @param description The room's description.
     */
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the route from this room to the destination,
     * if a route exists.
     * NB: This method does not work correctly.
     *     It oscillates between two rooms.
     * @param destination The destination.
     * @return The route, or null if there isn't one.
     */
    public String findRoute(String destination)
    {
        String route = null;
        // See if it is directly connected.
        Iterator<Entry<String, Room>> it = exits.entrySet().iterator();
        while(it.hasNext() && route == null) {
            Entry<String, Room> pair = it.next();
            if(pair.getValue().getName().equals(destination)) {
                route = pair.getKey();
            }
        }
        if(route != null) {
            return route;
        }
        else {
            // It is not directly connected.
            // See if it is connected to an adjacent room.
            it = exits.entrySet().iterator();
            while(it.hasNext() && route == null) {
                Entry<String, Room> pair = it.next();
                route = pair.getValue().findRoute(destination);
                if(route != null) {
                    route = pair.getKey() + " " + route;
                }
            }
            return route;
       }
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Get the room's name.
     * @return the room's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

