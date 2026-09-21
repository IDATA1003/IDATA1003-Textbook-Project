/**
 * Main class for the number display application.
 * Run some simple tests on the NumberDisplay class.
 */
public class Main {
  public static void main(String[] args) {
    NumberDisplay display = new NumberDisplay(60);
    display.setValue(59);
    System.out.println(display.getDisplayValue()); // Should print "59"
    display.increment();
    System.out.println(display.getDisplayValue()); // Should print "00"
    display.increment();
    System.out.println(display.getDisplayValue()); // Should print "01"
  }
}
