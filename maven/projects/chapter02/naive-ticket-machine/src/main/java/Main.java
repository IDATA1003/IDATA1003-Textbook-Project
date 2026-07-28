/**
 * The starting point of the application. This class holds the
 * main()-method making it possible to open and run the project in a professional IDE
 */
public class Main {
  public static void main(String[] args) {
    // Create a TicketMachine with ticket price 200
    TicketMachine ticketMachine = new TicketMachine(200);

    System.out.println("Balance: " + ticketMachine.getBalance());

    // By one ticket
    System.out.println("Insert 200 to the ticket machine.");
    ticketMachine.insertMoney(200);
    System.out.println("Balance: " + ticketMachine.getBalance());
    System.out.println("Buy ticket:");
    ticketMachine.printTicket();

    // Try to print a ticket without inserting money
    System.out.println("Try to print a ticket without inserting money:");
    ticketMachine.printTicket();

    // Check new balance
    System.out.println("Balance: " + ticketMachine.getBalance());
  }
}