/**
 * Provide a simple demonstration of the AddressBook class.
 * Sample data is added to the address book.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.1
 */
public class AddressBookDemo
{
    private AddressBook book;

    /**
     * Setup an AddressBook with sample data.
     * The address book can be retrieved using the getBook method. 
     */
    public AddressBookDemo()
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
        book = new AddressBook();
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }
    }

    /**
     * @return The sample address book.
     */
    public AddressBook getBook()
    {
        return book;
    }
}
