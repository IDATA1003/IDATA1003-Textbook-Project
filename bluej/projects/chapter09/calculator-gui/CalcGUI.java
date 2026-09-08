import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.EventHandler;

/**
 * A Calculator GUI in JavaFX.
 * Start this class by choosing 'Run JavaFX Application' from the class menu.
 *
 * @author Michael Kölling
 * @version 7.0
 */
public class CalcGUI extends Application
{
    private CalcEngine calc = new CalcEngine();
    private boolean showingVersion = true;
    
    private TextField calcDisplay;
    private Label infoLabel;
    
    private void infoClick(ActionEvent event) 
    {
        if (showingVersion) {
            infoLabel.setText(calc.getAuthor());
        }
        else {
            infoLabel.setText(calc.getVersion());
        }
        showingVersion = !showingVersion;
    }
    
    /**
     * The 'plus' button was clicked.
     */
    private void plusClick(ActionEvent event) 
    {
        calc.plus();
        redisplay();
    }
    
    /**
     * The 'minus' button was clicked.
     */
    private void minusClick(ActionEvent event) 
    {
        calc.minus();
        redisplay();
    }
    
    /**
     * The '=' button was clicked.
     */
    private void equalClick(ActionEvent event) 
    {
        calc.equals();
        redisplay();
    }
    
    /**
     * One of the number buttons was clicked.
     */
    private void numberClick(ActionEvent event) 
    {
        Button button = (Button) event.getSource();
        calc.numberPressed(Integer.parseInt(button.getText()));
        redisplay();
    }
    
    /**
     * The 'C' button was clicked.
     */
    private void clearClick(ActionEvent event) 
    {
        calc.clear();
        redisplay();
    }
    
    /**
     * Redisplay the current display value as defined by the calc engine.
     */
    private void redisplay()
    {
        calcDisplay.setText("" + calc.getDisplayValue());
    }
    
    /**
     * Start the application. First, build the GUI, then show it.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        // Create the three main elements: number display, buttons, and info label
        calcDisplay = new TextField("0");
        Pane buttons = makeButtonPane();
        infoLabel = new Label("Super Calculator");
        
        // Place them into a border pane for vertical placement.
        BorderPane root = new BorderPane​(buttons, calcDisplay, null, infoLabel, null);
        Insets insets = new Insets(4);
        root.setMargin(calcDisplay, insets);
        root.setMargin(infoLabel, insets);
        root.setAlignment(infoLabel, Pos.CENTER);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        
        stage.setTitle("JavaFX Calculator");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Create and return a GridPane with all the buttons needed
     * for the calculator interface.
     */
    private Pane makeButtonPane()
    {
        GridPane grid = new GridPane();
        
        // Populate the gridpane with all the buttons
        
        for (int i=0; i<=8; i++) {
            addButton(grid, Integer.toString(i+1), (i)%3, (i/3), this::numberClick);
        }
        
        addButton(grid, "C", 3, 0, this::clearClick);
        addButton(grid, "?", 3, 1, this::infoClick);

        addButton(grid, "0", 0, 3, this::numberClick);
        addButton(grid, "+", 1, 3, this::plusClick);
        addButton(grid, "-", 2, 3, this::minusClick);
        addButton(grid, "=", 3, 3, this::equalClick);

        return grid;
    }
    
    /**
     * Create a button with a given text and place it into the provided
     * GridPane in the specified row and column.
     * 
     * @param grid  The GridPane to place the button into
     * @param text  The text to appear on the button
     * @param col   The column to place the button into (0.. )
     * @param row   The row to place the button into (0.. )
     * @param callback The local method to be called when the button is clicked
     */
    private void addButton(GridPane grid, String text, int col, int row,
                           EventHandler<ActionEvent> callback)
    {
        Insets insets = new Insets(4);
        Button button = new Button(text);
        button.setOnAction(callback);
        grid.add(button, col, row);
        grid.setMargin(button, insets);
    }
}
