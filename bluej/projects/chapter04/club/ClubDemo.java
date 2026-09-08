
/**
 * Provide a demonstration of the Club and Membership
 * classes.
 * 
 * @author (your name) 
 * @version 7.0
 */
public class ClubDemo
{
    // instance variables - replace the example below with your own
    private Club club;

    /**
     * Constructor for objects of class ClubDemo
     */
    public ClubDemo()
    {
        club = new Club();
    }

    /**
     * Add some members to the club, and then
     * show how many there are.
     * Further example calls could be added if more functionality
     * is added to the Club class.
     */
    public void demo()
    {
        club.join(new Membership("David", 2, 2024));
        club.join(new Membership("Michael", 1, 2024));
        System.out.println("The club has " +
                           club.numberOfMembers() +
                           " members.");
    }
}
