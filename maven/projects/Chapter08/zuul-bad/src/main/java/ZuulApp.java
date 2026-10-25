/**
 * Represents the World-of-Zuul Application.
 * The class holds the {@code public static void main(String[] args)}-method
 * to start the application.
 */
public class ZuulApp {

  /**
   * Starts the application by creating and playing a new game.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}
