import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.Alert.AlertType;

import java.io.File;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * This version uses JavaFX.
 * 
 * To start the application, use BlueJ's 'Run JavaFX Application' function.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class ImageViewer extends Application
{
    // static fields:
    private static final String VERSION = "Version 1.0";

    // fields:
    private Stage stage;
    private Label imageLabel;
    private Label filenameLabel;
    private Label statusLabel;
    private OFImage currentImage;
    private FileChooser fileChooser;

    /**
     * Initialise the ImageViewer.
     */
    public ImageViewer()
    {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        currentImage = null;
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
     * Open and display the specified file.
     * @param file  The file top open. Must not be null.
     */
    private void openFile(File file)
    {
        currentImage = ImageFileManager.loadImage(file);
        
        if(currentImage == null) {   // image file was not a valid image
            showInvalidFileFormatError();
        }
        else {
            imageLabel.setGraphic(new ImageView(currentImage));
            showFilename(file.getPath());
            showStatus("File loaded.");
            stage.sizeToScene();
        }
    }

    /**
     * Show an error message to the user informing them that a file could not be
     * loaded because the format could not be read.
     */
    private void showInvalidFileFormatError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Image Load Error");
        alert.setHeaderText(null);  // Alerts have an optionl header. We don't want one.
        alert.setContentText("The file was not in a recognized image file format.");

        alert.showAndWait();
    }

    /**
     * Close function: close the current image.
     */
    private void closeAction(ActionEvent event)
    {
        currentImage = null;
        imageLabel.setGraphic(new ImageView(ImageFileManager.loadImage("emptyImage.png")));
        showFilename(null);
        stage.sizeToScene();
    }

    /**
     * The 'Quit' item has been activated.
     */
    private void quit(ActionEvent event)
    {
        //Platform.exit();  // currently throws Exception in BlueJ
        System.exit(0);
    }

    /**
     * 'Darker' function: make the picture darker.
     */
    private void makeDarker(ActionEvent event)
    {
        if(currentImage != null) {
            currentImage.darker();
            showStatus("Applied: darker");
        }
        else {
            showStatus("No image loaded.");
        }
    }

    /**
     * 'Lighter' function: make the picture lighter
     */
    private void makeLighter(ActionEvent event)
    {
        if(currentImage != null) {
            currentImage.lighter();
            showStatus("Applied: lighter");
        }
        else {
            showStatus("No image loaded.");
        }
    }

    /**
     * 'threshold' function: apply the threshold filter
     */
    private void threshold(ActionEvent event)
    {
        if(currentImage != null) {
            currentImage.threshold();
            showStatus("Applied: threshold");
        }
        else {
            showStatus("No image loaded.");
        }
    }

    /**
     * Show the 'About...' dialog.
     */
    private void showAbout(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About ImageViewer");
        alert.setHeaderText(null);  // Alerts have an optional header. We don't want one.
        alert.setContentText("ImageViewer\n" + VERSION);

        alert.showAndWait();
    }

    // ---- support methods ----

    /**
     * Display a file name on the appropriate label.
     * @param filename The file name to be displayed.
     */
    private void showFilename(String filename)
    {
        if(filename == null) {
            filenameLabel.setText("No file displayed.");
        }
        else {
            filenameLabel.setText("File: " + filename);
        }
    }

    /**
     * Display a status message in the frame's status bar.
     * @param text The status message to be displayed.
     */
    private void showStatus(String text)
    {
        statusLabel.setText(text);
    }

    // ---- JavaFX entry point and initial window construction ----

    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        VBox root = new VBox();
        makeMenuBar(root);

        filenameLabel = new Label();
        imageLabel = new Label("", new ImageView(ImageFileManager.loadImage("emptyImage.png")));
        statusLabel = new Label(VERSION);

        Pane contentPane = new BorderPane(imageLabel, filenameLabel, null, statusLabel, null);

        root.getChildren().add(contentPane);

        // building is done - arrange the components and show        
        showFilename(null);

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

        MenuItem closeItem = new MenuItem("Close");
        closeItem.setOnAction(this::closeAction);
        closeItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN));

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(this::quit);
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

        fileMenu.getItems().addAll(openItem, closeItem, quitItem);

        // create the Filter menu
        Menu filterMenu = new Menu("Filter");

        MenuItem darkerItem = new MenuItem("Darker");
        darkerItem.setOnAction(this::makeDarker);

        MenuItem lighterItem = new MenuItem("Lighter");
        lighterItem.setOnAction(this::makeLighter);

        MenuItem thresholdItem = new MenuItem("Threshold");
        thresholdItem.setOnAction(this::threshold);

        filterMenu.getItems().addAll(darkerItem, lighterItem, thresholdItem);

        // create the Help menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutItem = new MenuItem("About ImageViewer...");
        aboutItem.setOnAction(this::showAbout);

        helpMenu.getItems().addAll(aboutItem);

        menubar.getMenus().addAll(fileMenu, filterMenu, helpMenu);
    }
}
