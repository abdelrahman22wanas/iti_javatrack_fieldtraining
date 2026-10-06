package listview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * VID12 - Customized ListView (Demo).
 * <p>
 * A ListView of Contact objects rendered with a custom cell factory
 * (ContactListCell) instead of the default plain-text rows: each row
 * shows a colored avatar, name, email, and an online/away/offline dot.
 */
public class CustomListViewDemo extends Application {

    @Override
    public void start(Stage stage) {
        ObservableList<Contact> contacts = FXCollections.observableArrayList(
                new Contact("Ali Hassan", "ali.hassan@mail.com", "Online"),
                new Contact("Sara Mostafa", "sara.mostafa@mail.com", "Away"),
                new Contact("Omar Khaled", "omar.khaled@mail.com", "Offline"),
                new Contact("Mona Adel", "mona.adel@mail.com", "Online"),
                new Contact("Youssef Tarek", "youssef.tarek@mail.com", "Offline"),
                new Contact("Nour Ibrahim", "nour.ibrahim@mail.com", "Online")
        );

        ListView<Contact> listView = new ListView<>(contacts);
        // This is the key line: supplying a cell factory swaps in our
        // custom row layout for every item in the list.
        listView.setCellFactory(lv -> new ContactListCell());

        Label title = new Label("Contacts");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox root = new VBox(10, title, listView);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 380, 420);
        stage.setTitle("Customized ListView Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
