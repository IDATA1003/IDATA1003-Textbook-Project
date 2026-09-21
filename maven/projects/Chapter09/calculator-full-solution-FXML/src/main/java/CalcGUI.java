import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * A Calculator GUI in JavaFX, using FXML to build the GUI.
 * Start this class by choosing 'Run JavaFX Application' from the class menu.
 *
 * @author Michael Kölling
 * @version 7.1
 */
public class CalcGUI extends Application
{
    private CalcEngine calc = new CalcEngine();
    private boolean showingVersion = true;
    
    @FXML
    private TextField calcDisplay;
    @FXML
    private Label infoLabel;
    
    @FXML
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
    @FXML
    private void plusClick(ActionEvent event) 
    {
        calc.plus();
        redisplay();
    }
    
    @FXML
    private void minusClick(ActionEvent event) 
    {
        calc.minus();
        redisplay();
    }
    
    @FXML
    private void equalClick(ActionEvent event) 
    {
        calc.equals();
        redisplay();
    }
    
    @FXML
    private void numberClick(ActionEvent event) 
    {
        Button button = (Button) event.getSource();
        calc.numberPressed(Integer.parseInt(button.getText()));
        redisplay();
    }
    
    @FXML
    private void clearClick(ActionEvent event) 
    {
        calc.clear();
        redisplay();
    }
    
    private void redisplay()
    {
        calcDisplay.setText(""+calc.getDisplayValue());
    }
    
    @Override
    public void start(Stage stage) throws Exception
    {
        URL url = getClass().getResource("calc.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene (root);
        
        stage.setTitle("JavaFX Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
