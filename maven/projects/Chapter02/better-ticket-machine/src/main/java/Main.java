public class Main {
  public static void main(String[] args) {
    TicketMachine machine = new TicketMachine(50);
    System.out.println("Price: " + machine.getPrice());
    System.out.println("Inserting 70 cents...");
    machine.insertMoney(70);
    System.out.println("Balance: " + machine.getBalance());
    machine.printTicket();
    System.out.println("Balance: " + machine.getBalance());
    machine.printTicket();
    System.out.println("Balance: " + machine.getBalance());
  }
}
