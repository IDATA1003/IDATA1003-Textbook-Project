import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.*;

// TODO: save as

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * This version uses JavaFX.
 * 
 * To start the application, use BlueJ's 'Run JavaFX Application' function.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 3.0
 */
public class ImageViewer extends Application
{
    // static fields:
    private static final String VERSION = "Version 4.0";
    private static FileChooser fileChooser;

    // fields:
    private Stage stage;
    private Label imageLabel;
    private Label filenameLabel;
    private Label statusLabel;
    private Button smallerButton;
    private Button largerButton;
    private OFImage currentImage;

    private List<Filter> filters;

    /**
     * Initialise the ImageViewer.
     */
    public ImageViewer()
    {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        currentImage = null;
        filters = createFilters();
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
            return;
        }

        if (currentImage != null) {
            imageLabel.setGraphic(new ImageView(currentImage));
            setButtonsDisabled(false);
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
        Alert alert = new Alert(AlertType.INFORMATION);
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
        setButtonsDisabled(true);
        stage.sizeToScene();
    }

    /**
     * Save As function: save the current image to a file.
     */
    private void saveAs()
    {
        // if(currentImage != null) {
            // int returnVal = fileChooser.showSaveDialog(frame);
    
            // if(returnVal != JFileChooser.APPROVE_OPTION) {
                // return;  // cancelled
            // }
            // File selectedFile = fileChooser.getSelectedFile();
            // ImageFileManager.saveImage(currentImage, selectedFile);
            
            // showFilename(selectedFile.getPath());
        // }
    }

    /**
     * The 'Quit' item has been activated.
     */
    private void quit(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * Apply a given filter to the current image.
     * @param filter The filter to be applied.
     */
    private void applyFilter(Filter filter)
    {
        if(currentImage != null) {
            filter.apply(currentImage);
            showStatus("Applied: " + filter.getName());
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
        alert.setHeaderText(null);  // Alerts have an optionl header. We don't want one.
        alert.setContentText("ImageViewer\n" + VERSION);

        alert.showAndWait();
    }

    /**
     * Make the current picture larger.
     */
    private void makeLarger(ActionEvent event)
    {
        if(currentImage != null) {
            // create new image with double size
            int width = (int) currentImage.getWidth();
            int height = (int) currentImage.getHeight();
            OFImage newImage = new OFImage(width * 2, height * 2);

            // copy pixel data into new image
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    Color col = currentImage.getPixel(x, y);
                    newImage.setPixel(x * 2, y * 2, col);
                    newImage.setPixel(x * 2 + 1, y * 2, col);
                    newImage.setPixel(x * 2, y * 2 + 1, col);
                    newImage.setPixel(x * 2+1, y * 2 + 1, col);
                }
            }
            
            currentImage = newImage;
            imageLabel.setGraphic(new ImageView(currentImage));
            stage.sizeToScene();
        }
    }

    /**
     * Make the current picture smaller.
     */
    private void makeSmaller(ActionEvent event)
    {
        if(currentImage != null) {
            // create new image with double size
            int width = (int) currentImage.getWidth() / 2;
            int height = (int) currentImage.getHeight() / 2;
            OFImage newImage = new OFImage(width, height);

            // copy pixel data into new image
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    newImage.setPixel(x, y, currentImage.getPixel(x * 2, y * 2));
                }
            }
            
            currentImage = newImage;
            imageLabel.setGraphic(new ImageView(currentImage));
            stage.sizeToScene();
        }
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

    /**
     * Enable or disable all toolbar buttons.
     * 
     * @param status  'true' to enable the buttons, 'false' to disable.
     */
    private void setButtonsDisabled(boolean status)
    {
        smallerButton.setDisable(status);
        largerButton.setDisable(status);
    }

    /**
     * Create and return a list with all the known filters.
     * @return The list of filters.
     */
    private List<Filter> createFilters()
    {
        List<Filter> filterList = new ArrayList<>();
        filterList.add(new DarkerFilter("Darker"));
        filterList.add(new LighterFilter("Lighter"));
        filterList.add(new ThresholdFilter("Threshold"));
        filterList.add(new InvertFilter("Invert"));
        filterList.add(new SolarizeFilter("Solarize"));
        filterList.add(new SmoothFilter("Smooth"));
        filterList.add(new PixelizeFilter("Pixelize"));
        filterList.add(new MirrorFilter("Mirror"));
        filterList.add(new GrayScaleFilter("Grayscale"));
        filterList.add(new EdgeFilter("Edge Detection"));
        filterList.add(new FishEyeFilter("Fish Eye"));
        
        return filterList;
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
        filenameLabel.getStyleClass().add("infoLabel");
        imageLabel = new Label("", new ImageView(ImageFileManager.loadImage("emptyImage.png")));
        imageLabel.setId("image");
        statusLabel = new Label(VERSION);
        statusLabel.getStyleClass().add("infoLabel");
        
        smallerButton = new Button("Smaller");
        smallerButton.setOnAction(this::makeSmaller);
        smallerButton.setMaxWidth(Double.MAX_VALUE);
        
        largerButton = new Button("Larger");
        largerButton.setOnAction(this::makeLarger);
        largerButton.setMaxWidth(Double.MAX_VALUE);

        Pane toolBar = new VBox(smallerButton, largerButton);
        toolBar.setId("toolbar");
        
        Pane contentPane = new BorderPane(imageLabel, filenameLabel, null, statusLabel, toolBar);
        contentPane.setId("content");
        
        root.getChildren().add(contentPane);

        // building is done - arrange the components and show        
        showFilename(null);
        setButtonsDisabled(true);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("mystyle.css");

        stage.setTitle("ImageViewer");
        stage.setScene(scene);
        stage.show();
        
        // for testing only
        openFile(new File("gothic.jpg"));
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

        for(Filter filter : filters) {
            MenuItem item = new MenuItem(filter.getName());
            item.setOnAction(e -> applyFilter(filter));
            filterMenu.getItems().add(item);
        }

        // create the Help menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutItem = new MenuItem("About ImageViewer...");
        aboutItem.setOnAction(this::showAbout);

        helpMenu.getItems().addAll(aboutItem);

        menubar.getMenus().addAll(fileMenu, filterMenu, helpMenu);
    }
}
