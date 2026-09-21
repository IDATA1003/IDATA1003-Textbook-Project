public class Main {
  public static void main(String[] args) {
    ClockDisplay clock = new ClockDisplay(10, 30);
    System.out.println("Current time: " + clock.getTime());
    clock.timeTick();
    System.out.println("After one minute: " + clock.getTime());
  }
}
