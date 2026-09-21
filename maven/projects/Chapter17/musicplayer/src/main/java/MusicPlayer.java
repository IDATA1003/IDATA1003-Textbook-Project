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
import javafx.collections.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.*;

/**
 * A simple sound player with a JavaFX GUI.
 * 
 * The sound player provides an interface to the MusicOrganizer class
 * from chapter 4.
 * 
 * To start the application, use BlueJ's 'Run JavaFX Application' function.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class MusicPlayer extends Application
{
    private static final String VERSION = "Version 2.0";
    private static final String AUDIO_DIR = "../audio";
    
    private ListView<String> trackNameList;
    private Slider slider;

    private MusicOrganizer organizer;   // The library holding the music tracks
    private PlayerEngine player;        // A player for the music tracks
    private List<Track> trackList;      // The current track list

    /**
     * Create a SoundPlayer and display its GUI on screen.
     */
    public MusicPlayer()
    {
        organizer = new MusicOrganizer(AUDIO_DIR);
        player = new PlayerEngine();
    }
    
    /**
     * Play the sound file currently selected in the file list. If there is no
     * selection in the list, or if the selected file is not a sound file, 
     * do nothing.
     */
    private void play(ActionEvent event)
    {
        int index = trackNameList.getSelectionModel().getSelectedIndex();
        if(index >= 0 && index < trackList.size()) {
            slider.setValue(0);
            player.startPlaying(trackList.get(index).getFilename());
        }
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void stop(ActionEvent event)
    {
        player.stop();
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void pause(ActionEvent event)
    {
        player.pause();
    }

    /**
     * Resume a previously suspended sound file.
     */
    private void resume(ActionEvent event)
    {
        player.resume();
    }

    /**
     * A list item was clicked. If this was a double-click, play the track.
     */
    private void listClick(MouseEvent event)
    {
        if (event.getClickCount() == 2) {
            play(null);
        }
    }

    /**
     * Quit function: quit the application.
     */
    private void quit(ActionEvent event)
    {
        //Platform.exit();
        System.exit(0);
    }
    
    /**
     * About function: show the 'about' box.
     */
    private void showAbout(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Music Player");
        alert.setHeaderText("BlueJ Music Player");
        alert.setContentText("Written by Objects First Productions\n" + VERSION);

        alert.showAndWait();
    }
    
    /**
     * Set the content of the track list.
     * @param ordering The ordering to use.
     */
    private void setList(String order)
    {
        trackList = organizer.sortByField(order);
        List<String> tracks = getTracksDisplayList(trackList);
        ObservableList<String> items = FXCollections.observableArrayList(tracks);
        trackNameList.setItems(items);
    }

    /**
     * Reorder the track list on screen according to the combobox selection.
     */
    private void reorder(ActionEvent event)
    {
        ComboBox<String> orderOptions = (ComboBox<String>)event.getSource();
        String order = orderOptions.getValue();
        
        setList(order);
    }

    /**
     * Get a version of the track list formatted for display in the interface.
     * @param trackList The list of tracks to be displayed.
     * @return The tracks in display format.
     */
    private List<String> getTracksDisplayList(List<Track> trackList)
    {
        List<String> tracks = new ArrayList<>();
        for (Track track: trackList) {
            tracks.add(track.getArtist() + ": " + track.getTitle());
        }
        return tracks;
    }

    // ---- JavaFX entry point and initial window construction ----

    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Pane root = new VBox();
        makeMenuBar(root);

        Label imageLabel = new Label("", new ImageView(new Image("header.png")));
        Label orderLabel = new Label("Order by:");
        ComboBox<String> order = new ComboBox<String>();
        String[] orderOptions = Track.FIELDS;
        order.getItems().addAll(orderOptions);
        order.setOnAction(this::reorder);

        Pane topPane = new VBox(imageLabel, orderLabel, order);

        // Create the scrolled list for track listing.
        trackNameList = new ListView<>();
        trackNameList.setOnMouseClicked(this::listClick);
        //trackNameList.setMinWidth(600.0);
        setList("Artist");
        
        // Create the toolbar with the buttons
        
        Button playButton = new Button("Play");
        playButton.setOnAction(this::play);
        playButton.setMaxWidth(Double.MAX_VALUE);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(this::stop);
        stopButton.setMaxWidth(Double.MAX_VALUE);

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(this::pause);
        pauseButton.setMaxWidth(Double.MAX_VALUE);

        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(this::resume);
        resumeButton.setMaxWidth(Double.MAX_VALUE);

        slider = new Slider();
        Pane toolBar = new TilePane(playButton, stopButton, pauseButton, resumeButton);
        toolBar.setId("toolbar");
        
        Pane bottomPane = new VBox(slider, toolBar);
        bottomPane.setId("bottom");

        Pane contentPane = new BorderPane(trackNameList, topPane, null, bottomPane, null);
        contentPane.setId("content");

        root.getChildren().add(contentPane);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("mystyle.css");

        stage.setTitle("MusicPlayer");
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

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(this::quit);
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

        fileMenu.getItems().addAll(quitItem);

        // create the Help menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutItem = new MenuItem("About Music Player...");
        aboutItem.setOnAction(this::showAbout);

        helpMenu.getItems().addAll(aboutItem);

        menubar.getMenus().addAll(fileMenu, helpMenu);
    }    
}
