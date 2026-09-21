/**
 * Main class for the auction application.
 * Tests the Action class with some simple test cases.
 */
public class Main {
  public static void main(String[] args) {
    // Test cases for the Action class
    Auction auction = new Auction();
    auction.enterLot("Antique vase");
    auction.enterLot("Vintage car");
    auction.showLots();

    Person bidder1 = new Person("Alice");
    Person bidder2 = new Person("Bob");
    auction.makeABid(1, bidder1, 100);
    auction.makeABid(1, bidder2, 150);
    auction.makeABid(2, bidder1, 200);
    auction.showLots();
  }
}
