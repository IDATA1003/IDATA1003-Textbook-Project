

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;

/**
 * Write a description of JavaFX class Components here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Components extends Application
{
    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Label label = new Label("Simple label");
        Image image = new Image(getClass().getResourceAsStream("bluej.png"));
        Label label2 = new Label("Label with icon", new ImageView(image));      
        Button button = new Button("Button");
        RadioButton radio = new RadioButton("Radio Button");
        ToggleButton toggle = new ToggleButton("Toggle Button");
        CheckBox check = new CheckBox("Check Box");
        TextField text = new TextField("Text Field");
        
        
        VBox root = new VBox();
        root.setPadding(new Insets(50, 50, 50, 50));
        root.setSpacing(20);
        
        root.getChildren().addAll(label, label2, button, radio, toggle, check, text);

        Scene scene = new Scene(root);

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }

}
