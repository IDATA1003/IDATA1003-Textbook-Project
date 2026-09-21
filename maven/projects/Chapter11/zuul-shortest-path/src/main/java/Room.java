import java.util.*;
import java.util.Map.Entry;

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
 * @version 7.3
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
     * @param destination The destination.
     * @param roomList The list of rooms.
     * @return The route, or null if there isn't one.
     */
    public String findRoute(String destination, List<Room> roomList)
    {
        // The current best distances from the start point
        // to the destination.
        Map<Room, Integer> distances = new HashMap<>();
        // How each room was reached from an adjacent room on the
        // best route found.
        Map<Room, ExitFromRoom> previous = new HashMap<>();
        // Initially, there are no known distances or routes to
        // the destination.
        for(Room room : roomList) {
            distances.put(room, Integer.MAX_VALUE);
            previous.put(room, null);
        }
        // The distance from the starting room to itself is zero.
        distances.put(this, 0);

        // Keep track of which rooms have already been visited and which not.
        Set<Room> alreadyVisited = new HashSet<>();
        Set<Room> notYetVisited = new HashSet<>();

        notYetVisited.add(this);
        // Set destinationRoom once it has been reached on the
        // shortest path.
        Room destinationRoom = null;
        while(destinationRoom == null && ! notYetVisited.isEmpty()) {
            Room roomToConsider = findClosest(notYetVisited, distances);
            if(roomToConsider.getName().equals(destination)) {
                destinationRoom = roomToConsider;
            }
            else {
                notYetVisited.remove(roomToConsider);
                alreadyVisited.add(roomToConsider);
                // Assume all rooms are 1 move away.
                int distanceToNeighbor = distances.get(roomToConsider) + 1;
                for (Entry<String, Room> pair : roomToConsider.exits.entrySet()) {
                    Room neighbor = pair.getValue();
                    if (!alreadyVisited.contains(neighbor)) {
                        int currentNeighborDistance = distances.get(neighbor);
                        if (currentNeighborDistance == Integer.MAX_VALUE ||
                                distanceToNeighbor < currentNeighborDistance) {
                            // This is a shorter route to the neighbor.
                            distances.put(neighbor, distanceToNeighbor);
                            previous.put(neighbor, new ExitFromRoom(pair.getKey(), roomToConsider));
                            notYetVisited.add(neighbor);
                        }
                    }
                }
            }
        }
        if(destinationRoom != null) {
            // Extract the path by working backwards to the start point.
            String route = "";
            Room r = destinationRoom;
            while (r != this) {
                ExitFromRoom whereFrom = previous.get(r);
                route = whereFrom.exit() + " " + route;
                r = whereFrom.fromRoom();
            }
            return route;
        }
        else {
            return "There is no route from " + name + " to " + destination;
        }
    }

    /**
     * Find which of the unvisited rooms is the closest neighbor.
     * @param unvisited The unvisited rooms.
     * @param distances The current distance estimates.
     * @return The room that is currently thought to be the closest neighbor.
     */
    private Room findClosest(Set<Room> unvisited, Map<Room, Integer> distances) {
        List<DistanceToRoom> closest = new ArrayList<>();
        for(Room r : unvisited) {
            closest.add(new DistanceToRoom(r, distances.get(r)));
        }
        closest.sort((d1, d2) -> d1.distance() - d2.distance());
        return closest.get(0).room();
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

    /**
     * Pair a room and the current shortest distance to reach it.
     * @param room The room.
     * @param distance The current shortest distance to the room.
     */
    record DistanceToRoom(Room room, int distance)
    {
    }

    /**
     * Pair a direction and the room exited in that direction.
     * @param exit The exit direction.
     * @param fromRoom The room exited.
     */
    record ExitFromRoom(String exit, Room fromRoom)
    {
    }
}

