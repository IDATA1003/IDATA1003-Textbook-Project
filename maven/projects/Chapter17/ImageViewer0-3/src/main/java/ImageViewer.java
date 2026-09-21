import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.event.*;

/**
 * A very simple JavaFX demo class.
 *
 * @author Michael Kölling
 * @version 0.3
 */
public class ImageViewer extends Application
{
    private static final String VERSION = "Version 0.3";

    private Stage stage;
    private Label imageLabel;
    private Label filenameLabel;
    private Label statusLabel;

    // ---- implementation of menu functions ----

    /**
     * The 'Open' item has been activated.
     */
    private void openAction(ActionEvent event)
    {
        System.out.println("Open");
        // TODO: implement properly later
    }
    
    /**
     * The 'Quit' item has been activated.
     */
    private void quitAction(ActionEvent event)
    {
        System.exit(0);
    }
    
    /**
     * The 'About ImageViewer' item has been activated.
     */
    private void aboutAction(ActionEvent event)
    {
        System.out.println("About Imageviewer...");
        // TODO: implement properly later
    }
    
    // ---- JavaFX entry point and initial window construction ----

    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        Pane root = new VBox();
        makeMenuBar(root);   // this adds the menu to the root pane

        filenameLabel = new Label("No image loaded");
        imageLabel = new Label("", new ImageView(new Image("emptyImage.png")));
        statusLabel = new Label(VERSION);

        Pane contentPane = new BorderPane(imageLabel, filenameLabel, null, statusLabel, null);

        root.getChildren().add(contentPane);

        Scene scene = new Scene(root);
        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(Pane parent)
    {
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);
        
        // create the File menu
        Menu fileMenu = new Menu("File");
        
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(this::openAction);

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(this::quitAction);

        fileMenu.getItems().addAll(openItem, quitItem);

        // create the Help menu
        Menu helpMenu = new Menu("Help");
        
        MenuItem aboutItem = new MenuItem("About ImageViewer...");
        aboutItem.setOnAction(this::aboutAction);

        helpMenu.getItems().addAll(aboutItem);

        // add all menus to the menu bar
        menubar.getMenus().addAll(fileMenu, helpMenu);
    }
}
