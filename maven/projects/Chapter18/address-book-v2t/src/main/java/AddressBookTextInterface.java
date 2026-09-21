/**
 * Provide a textual interface to an AddressBook.
 * Different commands provide access to the data in the address book.
 *
 *      One to search the address book.
 *      One to allow a set of contact details to be entered.
 *      One to show all the entries in the book.
 *      One to get the details for a given entry.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.1
 */
public class AddressBookTextInterface
{
    // The address book to be viewed and manipulated.
    private AddressBook book;
    // A parser for handling user commands.
    private Parser parser;
    
    /**
     * Constructor for objects of class AddressBookTextInterface
     * @param book The address book to be manipulated.
     */
    public AddressBookTextInterface()
    {
        book = new AddressBook();
        addSampleData(book);

        parser = new Parser();
        run();
    }
    
    /**
     * Add some sample data to the address book for testing purposes.
     */
    private void addSampleData(AddressBook book)
    {
        ContactDetails[] sampleDetails = {
                new ContactDetails("david",   "08459 100000", "address 1"),
                new ContactDetails("michael", "02089 200000", "address 2"),
                new ContactDetails("john",    "02089 300000", "address 3"),
                new ContactDetails("helen",   "08459 400000", "address 4"),
                new ContactDetails("emma",    "08333 500000", "address 5"),
                new ContactDetails("kate",    "08411 600000", "address 6"),
                new ContactDetails("kai",     "02072 700000", "address 7"),
                new ContactDetails("himari",  "02088 800000", "address 8"),
            };
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }        
    }

    /**
     * Read a series of commands from the user to interact
     * with the address book. Stop when the user types 'quit'.
     */
    public void run()
    {
        System.out.println("Address Book.");
        System.out.println("Type 'help' for a list of commands.");
        
        String command;
        do {
            command = parser.getCommand();
            
            switch (command) {
                case "add" -> add();
                case "get" -> get();
                case "list" -> list();
                case "search" -> find();
                case "help" -> help();
            }

        } while(!(command.equals("quit")));

        System.out.println("Goodbye.");
    }
    
    /**
     * Add a new entry.
     */
    private void add()
    {
        System.out.print("Name: ");
        String name = parser.readLine();
        System.out.print("Phone: ");
        String phone = parser.readLine();
        System.out.print("Address: ");
        String address = parser.readLine();
        book.addDetails(new ContactDetails(name, phone, address));
    }
    
    /**
     * Find an entry matching a key.
     */
    private void get()
    {
        System.out.println("Type the key of the entry.");
        String key = parser.readLine();
        ContactDetails result = book.getDetails(key);
        System.out.println(result);
    }
    
    /**
     * Find entries matching a key prefix.
     */
    private void find()
    {
        System.out.println("Type a prefix of the key to be found.");
        String prefix = parser.readLine();
        ContactDetails[] results = book.search(prefix);
        for(int i = 0; i < results.length; i++){
            System.out.println(results[i]);
            System.out.println("=====");
        }
    }
    
    /**
     * List the available commands.
     */
    private void help()
    {
        parser.showCommands();
    }
    
    /**
     * List the address book's contents.
     */
    private void list()
    {
        System.out.println(book.listDetails());
    }
}
