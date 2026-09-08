
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.Stage;

/**
 * Provide a GUI view of an AddressBook.
 * Different panes provide access to the data in the address book.
 *
 *      One to search the address book.
 *
 *      One to allow a set of contact details to be entered.
 *      The add button adds the data to the address book.
 *
 *      One to show all the entries in the book.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 7.0
 */
public class AddressBookGUI extends Application
{
    // The address book to be viewed and manipulated.
    private AddressBook book;

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        book = new AddressBook();
        addSampleData(book);
        
        createAndShowGUI(stage);
    }

    /**
     * Add some sample data to the address book for testing purposes.
     */
    private void addSampleData(AddressBook book)
    {
        ContactDetails[] sampleDetails = {
                new ContactDetails("david",   "08459 100000", "address 1"),
                new ContactDetails("michael", "02089 200000", "address 2"),
                new ContactDetails("john",    "02089 300000", "address 3"),
                new ContactDetails("helen",   "08459 400000", "address 4"),
                new ContactDetails("emma",    "08333 500000", "address 5"),
                new ContactDetails("kate",    "08411 600000", "address 6"),
                new ContactDetails("kai",     "02072 700000", "address 7"),
                new ContactDetails("himari",  "02088 800000", "address 8"),
            };
        for(ContactDetails details : sampleDetails) {
            book.addDetails(details);
        }        
    }

    /**
     * Show results of a prefix search
     */
    private void showSearchResults(TextField searchField, TextArea results)
    {
        String searchString = searchField.getText();
        StringBuilder buffer = new StringBuilder();
        if(searchString.length() > 0) {
            ContactDetails[] contacts = book.search(searchString);
            for(int i = 0; i < contacts.length; i++) {
                buffer.append(contacts[i]).append("\n\n");
            }
        }
        results.setText(buffer.toString());

    }

    /**
     * Create the GUI for this application and show it on screen.
     */
    private void createAndShowGUI(Stage stage)
    {
        Tab searchTab = createSearchTab();
        Tab dataEntryTab = createDataEntryTab();
        Tab listTab = createListTab();

        TabPane tabPane = new TabPane(searchTab, dataEntryTab, listTab);

        Scene scene = new Scene(tabPane);
        scene.getStylesheets().add("style.css");
        
        stage.setTitle("Address Book");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Create a tab for the search function.
     * @return The tab, fully intialised and set up.
     */
    private Tab createSearchTab()
    {
        Tab tab = new Tab("Search the Entries");

        Label searchLabel = new Label("Search");
        searchLabel.setId("searchLabel");
        TextField searchField = new TextField();

        Pane searchPane = new BorderPane(searchField, null, null, null, searchLabel);
        searchPane.setId("searchPane");

        TextArea results = new TextArea();
        results.setEditable(false);
        results.setFocusTraversable(false);

        searchField.setOnKeyTyped(e -> showSearchResults(searchField, results));

        Pane contentPane = new BorderPane(results, searchPane, null, null, null);
        //searchField.requestFocus();

        tab.setContent(contentPane);

        return tab;
    }

    /**
     * Create a tab for the search function.
     * @return The tab, fully intialised and set up.
     */
    private Tab createDataEntryTab()
    {
        Tab tab = new Tab("Enter New Details");

        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();

        Label phoneLabel = new Label("Phone number");
        TextField phoneField = new TextField();

        Label addressLabel = new Label("Address");
        TextArea addressField = new TextArea();

        Pane dataPane = new VBox(nameLabel, nameField, phoneLabel, phoneField, addressLabel, addressField);

        Button addButton = new Button("Add");
        addButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setOnAction(e -> book.addDetails(
                    new ContactDetails(nameField.getText(),
                        phoneField.getText(),
                        addressField.getText()))
        );

        Button clearButton = new Button("Clear");
        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setOnAction(e -> {
                    nameField.setText("");
                    phoneField.setText("");
                    addressField.setText("");
            }
        );

        Pane buttons = new TilePane(addButton, clearButton);
        buttons.setId("buttons");

        Pane contentPane = new BorderPane(dataPane, null, null, buttons, null);
        tab.setContent(contentPane);

        return tab;        
    }

    /**
     * Create a tab for the search function.
     * @return The tab, fully intialised and set up.
     */
    private Tab createListTab()
    {
        Tab tab = new Tab("List the Entries");

        TextArea text = new TextArea();
        text.setEditable(false);

        Button listButton = new Button("List");
        listButton.setMaxWidth(Double.MAX_VALUE);
        listButton.setOnAction(e -> text.setText(book.listDetails()));

        Button clearButton = new Button("Clear");
        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setOnAction(e -> text.setText(""));

        Pane buttons = new TilePane(listButton, clearButton);
        buttons.setId("buttons");

        Pane contentPane = new BorderPane(text, null, null, buttons, null);
        tab.setContent(contentPane);

        return tab;
    }
}
