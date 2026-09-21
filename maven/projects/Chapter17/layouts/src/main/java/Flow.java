

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
 * Write a description of JavaFX class Layout here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Flow extends Application
{
     /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Button b1 = new Button("First");
        Button b2 = new Button("Second");
        Button b3 = new Button("This text is much longer");
        Button b4 = new Button("Fourth");
        Button b5 = new Button("Fifth");
        
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(10);
        root.setVgap(10);
        
        root.getChildren().addAll(b1, b2, b3, b4, b5);

        Scene scene = new Scene(root);

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }

}
