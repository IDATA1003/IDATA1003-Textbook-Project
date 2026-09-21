import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.input.*;

import java.io.File;

/**
 * The top level class of an image viewer application. This class sets up the
 * interface and starts the application.
 *
 * @author Michael Kölling
 * @version 0.4
 */
public class ImageViewer extends Application
{
    private static final String VERSION = "Version 0.4";

    private Stage stage;
    private Label imageLabel;
    private Label filenameLabel;
    private Label statusLabel;
    private FileChooser fileChooser;
    
    /**
     * Initialise the ImageViewer.
     */
    public ImageViewer()
    {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    }

    // ---- implementation of menu functions ----

    /**
     * The 'Open' item has been activated.
     */
    private void openAction(ActionEvent event)
    {
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {   // if the dialogue is cancelled, file is null
            openFile(file);
        }
    }
    
    /**
     * Open and display the specified image file.
     * @param file  The file to open. Must not be null.
     */
    private void openFile(File file)
    {
        OFImage currentImage = ImageFileManager.loadImage(file);
        
        if (currentImage != null) {
            imageLabel.setGraphic(new ImageView(currentImage));
            stage.sizeToScene();
        }
    }

    /**
     * The 'Quit' item has been activated.
     */
    private void quitAction(ActionEvent event)
    {
        //Platform.exit();  // currently throws Exception in BlueJ
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
        imageLabel = new Label("", new ImageView(ImageFileManager.loadImage("emptyImage.png")));
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
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(this::quitAction);
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

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
