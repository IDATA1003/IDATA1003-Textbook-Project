

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
public class Border extends Application
{
     /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Button b1 = new Button("Top");        
        Button b2 = new Button("Left");
        Button b3 = new Button("Center");
        Button b4 = new Button("Right");
        Button b5 = new Button("Bottom");
        
        b1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b5.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        
        root.setTop(b1);
        root.setLeft(b2);
        root.setCenter(b3);
        root.setRight(b4);
        root.setBottom(b5);

        Scene scene = new Scene(root);

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }

}
