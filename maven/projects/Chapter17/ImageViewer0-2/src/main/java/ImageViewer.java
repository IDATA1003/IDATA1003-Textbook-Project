import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * This version uses JavaFX.
 * 
 * To start the application, use BlueJ's 'Run JavaFX Application' function.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 0.2
 */
public class ImageViewer extends Application
{
    private Stage stage;
    private Label imageLabel;
    private Label filenameLabel;
    private Label statusLabel;

    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        filenameLabel = new Label("No image loaded");
        imageLabel = new Label("", new ImageView(new Image("lords.jpg")));
        statusLabel = new Label("Version 0.2");

        Pane contentPane = new BorderPane(imageLabel, filenameLabel, null, statusLabel, null);

        Scene scene = new Scene(contentPane);

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }
}
