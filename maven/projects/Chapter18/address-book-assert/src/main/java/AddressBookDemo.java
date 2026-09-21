/**
 * A demo class for AddressBook.
 * Some basic test methods are included. The purpose of these
 * is to see whether assertion errors are triggered for some
 * of the basic operations on an address book.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class AddressBookDemo
{
    // Sample contact details.
    private ContactDetails[] sampleDetails;
    // An address book containing sample data.
    private AddressBook book;
    // A copy of one of the contact's details.
    private ContactDetails existingContact;
    // An additional contact.
    private ContactDetails furtherContact;

    /**
     * Setup some sample data.
     */
    public AddressBookDemo()
    {
        // A sample of contact details to be stored in the book.
        sampleDetails = new ContactDetails[] {
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
        // Take a copy of the details of one of the contacts.
        ContactDetails first = sampleDetails[0];
        existingContact = new ContactDetails(first.name(), first.phone(),
                                     first.address());
        // Create a further contact who is not yet in the address book.
        furtherContact = new ContactDetails("sophie", "08459 900000", "address 9");
    }

    /**
     * Setup a new AddressBook with sample data.
     */
    public void setup()
    {
        book = new AddressBook();
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }
    }
    
    /**
     * A simple test of addDetails to see if an assertion fails.
     */
    public void testAddition()
    {
        setup();
        book.addDetails(furtherContact);
    }
    
    /**
     * A simple test of removeDetails to see if an assertion fails.
     */
    public void testRemoval()
    {
        setup();
        book.removeDetails(existingContact.name());
    }
    
    /**
     * A simple test of changeDetails to see if an assertion fails.
     */
    public void testChange()
    {
        setup();
        ContactDetails revisedDetails = createRevisedDetails();
            
        book.changeDetails(existingContact.name(), revisedDetails);
    }
    
    /**
     * Add for a second time an entry with duplicate name
     * and phone details.
     * This should trigger an AssertionError.
     */
    public void testForAdditionError()
    {
        setup();
        book.addDetails(createRevisedDetails());
    }

    /**
     * @return The sample address book.
     */
    public AddressBook getBook()
    {
        return book;
    }

    /**
     * @return The details of a contact who is originally in
     *         the address book.
     */
    public ContactDetails getExistingContact()
    {
        return existingContact;
    }

    /**
     * @return The details of a further contact, not originally
     *         in the address book.
     */
    public ContactDetails getFurtherContact()
    {
        return furtherContact;
    }
    
    /**
     * Create a contact based on an existing contact.
     */
    private ContactDetails createRevisedDetails()
    {
        return new ContactDetails(existingContact.name(),
                                  existingContact.phone(),
                                  existingContact.address() + "X");
    }
}
