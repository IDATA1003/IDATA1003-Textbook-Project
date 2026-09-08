import java.util.ArrayList;
import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 7.3
 */

public class Game 
{
    // Parser for reading user commands.
    private final Parser parser;
    // The current room.
    private Room currentRoom;
    // The list of all rooms.
    private final List<Room> roomList;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        roomList = new ArrayList<>();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, entrance, livingRoom, kitchen, garden;
        Room study, gamesRoom, extension;
      
        // create the rooms
        outside = new Room("outside", "outside the house");
        entrance = new Room("entrance", "in the entrance of the house");
        livingRoom = new Room("living-room", "in the living room");
        kitchen = new Room("kitchen", "in the kitchen");
        garden = new Room("garden", "in the garden");
        study = new Room("study", "in the study");
        gamesRoom = new Room("games-room","in the games room");
        extension = new Room("extension", "in the extension");

        roomList.addAll(
                List.of(outside, entrance, livingRoom,
                        kitchen, garden, study, gamesRoom,
                        extension));
        
        // initialise room exits
        outside.setExit("north", entrance);

        entrance.setExit("south", outside);
        entrance.setExit("west", livingRoom);
        entrance.setExit("north", kitchen);
        entrance.setExit("east", gamesRoom);

        livingRoom.setExit("east", entrance);
        livingRoom.setExit("north", study);

        kitchen.setExit("south", entrance);
        kitchen.setExit("north", garden);
        kitchen.setExit("west", study);

        garden.setExit("south", kitchen);
        garden.setExit("east", extension);

        study.setExit("south", livingRoom);
        study.setExit("east", kitchen);

        gamesRoom.setExit("west", entrance);
        gamesRoom.setExit("north", extension);

        extension.setExit("west", garden);
        extension.setExit("south", gamesRoom);

        currentRoom = outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch(commandWord) {
            case "go":
                goRoom(command);
                break;
            case "help":
                printHelp();
                break;
            case "rooms":
                listRooms();
                break;
            case "route":
                findRoute(command, roomList);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            default:
                // else command not recognised.
                break;
        }


        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * List the room names.
     */
    private void listRooms()
    {
        System.out.print("Rooms: ");
        for(Room room : roomList) {
            System.out.print(room.getName() + " ");
        }
        System.out.println();
    }
    /**
     * Try to find a route to the given room.
     * @param command  The "route" command and its parameter.
     * @param roomList The list of all rooms.
     */
    private void findRoute(Command command, List<Room> roomList)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Route to where?");
            return;
        }

        String destination = command.getSecondWord();
        if(destination.equals(currentRoom.getName())) {
            System.out.println("You are already there!");
        }
        else {
            String route = currentRoom.findRoute(destination, roomList);
            if(route != null) {
                System.out.println(route);
            }
            else {
                System.out.println("There is no route!");
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            // signal that we want to quit
            return true;
        }
    }
}
