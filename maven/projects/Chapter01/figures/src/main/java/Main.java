/**
 * This program demonstrates the use of the Circle and Square classes.
 * It creates a circle and a square, makes them visible, and moves them horizontally.
 */
public class Main {
  static void main(String[] args) {
    Circle circle = new Circle();
    circle.makeVisible();
    circle.moveHorizontal(100);

    Square square  = new Square();
    square.makeVisible();
    square.moveHorizontal(-100);
  }
}
