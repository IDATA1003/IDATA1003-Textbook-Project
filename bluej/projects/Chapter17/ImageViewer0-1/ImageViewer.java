import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * ImageViewer is the main class of the image viewer application. It builds
 * and displays the application GUI and initialises all other components.
 * 
 * This application uses JavaFX. To start it, use the BlueJ 
 * 'Run JavaFX Application' option in the class's context menu.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1
 */
public class ImageViewer extends Application
{
    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Label myLabel = new Label("I am a label. I can display some text.");

        StackPane root = new StackPane();
        root.getChildren().add(myLabel);

        Scene scene = new Scene(root, 300, 100);

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }
}


