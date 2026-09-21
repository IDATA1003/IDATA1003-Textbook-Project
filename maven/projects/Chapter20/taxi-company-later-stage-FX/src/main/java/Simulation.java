import javafx.application.Application;
//import javafx.application.Platform;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.*;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Run the simulation by asking a collection of actors to act.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 */
public class Simulation extends Application
{
    private static final String VERSION = "Version 0.2";

    private final List<Actor> actors;
    private int step;
    private City city;
    private CityView cityView;
    
    /**
     * Create the initial set of actors for the simulation.
     */
    public Simulation()
    {
        actors = new ArrayList<>();
        step = 0;

        city = new City();
        TaxiCompany company = new TaxiCompany(city);
        PassengerSource source = new PassengerSource(city, company);
        
        actors.addAll(company.getVehicles());
        actors.add(source);
    }
    
    // ---- JavaFX event handlers ----

    /**
     * Take a single step of the simulation.
     */
    private void step(ActionEvent event)
    {
        for(Actor actor : actors) {
            actor.act();
            cityView.update();
        }
    }

    /**
     * Run the simulation for a fixed number of steps.
     * Pause after each step to slow down the animation.
     */
    private void run(ActionEvent event)
    {
        new AnimationTimer() {
            int stepCount = 0;
            @Override
            public void handle(long now) {
                step(null);
                delay(30);
                stepCount++;
                if(stepCount >= 200) {
                    stop();
                }
            }
        }.start();
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
        alert.setTitle("About Taxiville");
        alert.setHeaderText("Taxiville");
        alert.setContentText("Written by Objects First Productions\n" + VERSION);

        alert.showAndWait();
    }
    
    // ---- JavaFX entry point ----

    /**
     * The main entry point for JavaFX programs.
     */
    @Override
    public void start(Stage stage)
    {
        Pane root = new VBox();
        makeMenuBar(root);
        
        // Create the toolbar with the buttons
        Button stepButton = new Button("Step");
        stepButton.setOnAction(this::step);
        stepButton.setMaxWidth(Double.MAX_VALUE);

        Button runButton = new Button("Run");
        runButton.setOnAction(this::run);
        runButton.setMaxWidth(Double.MAX_VALUE);

        Pane toolBar = new TilePane(stepButton, runButton);
        toolBar.setId("toolbar");
        
        cityView = new CityView(city);
        
        Pane contentPane = new BorderPane(cityView, toolBar, null, null, null);
        contentPane.setId("content");

        root.getChildren().add(contentPane);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("guistyle.css");
        stage.setTitle("Taxiville");
        stage.setScene(scene);
        stage.show();
        
        cityView.update();
    }

    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(Pane parent)
    {
        // create the File menu
        Menu fileMenu = new Menu("File");

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(this::quit);
        quitItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));

        fileMenu.getItems().addAll(quitItem);

        // create the Help menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutItem = new MenuItem("About Taxiville...");
        aboutItem.setOnAction(this::showAbout);

        helpMenu.getItems().addAll(aboutItem);

        // make the menubar with all the menus
        MenuBar menubar = new MenuBar(fileMenu, helpMenu);

        parent.getChildren().add(menubar);
    }    
    
    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to cause a small delay.
     * @param milliseconds The number of milliseconds to wait.
     */
    private void delay(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e)
        {
            // ignore the exception
        }
    }    
}
