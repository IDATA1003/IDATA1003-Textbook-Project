import java.io.Serializable;

/**
 * Name, address and telephone number details.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.3
 */
public record ContactDetails(String name, String phone, String address)
    implements Comparable<ContactDetails>, Serializable
{
    /**
     * Set up the contact details. All details are trimmed to remove
     * trailing white space.
     * @param name The name.
     * @param phone The phone number.
     * @param address The address.
     */
    public ContactDetails(String name, String phone, String address)
    {
        // Use blank strings if any of the arguments is null.
        if(name == null) {
            name = "";
        }
        if(phone == null) {
            phone = "";
        }
        if(address == null) {
            address = "";
        }
        this.name = name.trim();
        this.phone = phone.trim();
        this.address = address.trim();
    }
    
    /**
     * @return The name.
     */
    public String name()
    {
        return name;
    }

    /**
     * @return The telephone number.
     */
    public String phone()
    {
        return phone;
    }

    /**
     * @return The address.
     */
    public String address()
    {
        return address;
    }
    
    /**
     * Compare these details against another set, for the purpose
     * of sorting. The fields are sorted by name, phone, and address.
     * @param otherDetails The details to be compared against.
     * @return a negative integer if this comes before the parameter,
     *         zero if they are equal and a positive integer if this
     *         comes after the second.
     */
    public int compareTo(ContactDetails otherDetails)
    {
        int comparison = name.compareTo(otherDetails.name());
        if(comparison != 0){
            return comparison;
        }
        comparison = phone.compareTo(otherDetails.phone());
        if(comparison != 0){
            return comparison;
        }
        return address.compareTo(otherDetails.address());
    }

    /**
     * @return A multi-line string containing the name, phone, and address.
     */
    public String toString()
    {
        return name + "\n" + phone + "\n" + address;
    }
}
