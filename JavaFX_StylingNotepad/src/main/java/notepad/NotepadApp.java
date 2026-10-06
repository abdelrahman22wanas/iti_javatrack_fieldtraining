package notepad;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Task 2 - JFX Notepad.
 * A simple Notepad-style text editor: menu bar (File / Edit / Help) +
 * a large multi-line text area. All colors/fonts come from notepad.css.
 */
public class NotepadApp extends Application {

    private final TextArea textArea = new TextArea();
    private Stage stage;
    private Path currentFile;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        textArea.setId("editor-area");
        textArea.setWrapText(true);

        MenuBar menuBar = buildMenuBar();
        menuBar.setId("app-menu-bar");

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 700, 550);
        scene.getStylesheets().add(getClass().getResource("/notepad/notepad.css").toExternalForm());

        updateTitle();
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar buildMenuBar() {
        // --- File menu ---
        MenuItem newItem = new MenuItem("New");
        newItem.setOnAction(e -> newFile());

        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> openFile());

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(e -> saveFile(false));

        MenuItem saveAsItem = new MenuItem("Save As");
        saveAsItem.setOnAction(e -> saveFile(true));

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));

        Menu fileMenu = new Menu("File", null, newItem, openItem, saveItem, saveAsItem,
                new SeparatorMenuItem(), exitItem);

        // --- Edit menu ---
        MenuItem cutItem = new MenuItem("Cut");
        cutItem.setOnAction(e -> textArea.cut());

        MenuItem copyItem = new MenuItem("Copy");
        copyItem.setOnAction(e -> textArea.copy());

        MenuItem pasteItem = new MenuItem("Paste");
        pasteItem.setOnAction(e -> textArea.paste());

        MenuItem selectAllItem = new MenuItem("Select All");
        selectAllItem.setOnAction(e -> textArea.selectAll());

        Menu editMenu = new Menu("Edit", null, cutItem, copyItem, pasteItem, selectAllItem);

        // --- Help menu ---
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());

        Menu helpMenu = new Menu("Help", null, aboutItem);

        return new MenuBar(fileMenu, editMenu, helpMenu);
    }

    private void newFile() {
        textArea.clear();
        currentFile = null;
        updateTitle();
    }

    private void openFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Text File");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        var file = chooser.showOpenDialog(stage);
        if (file == null) return;

        try {
            String content = Files.readString(file.toPath());
            textArea.setText(content);
            currentFile = file.toPath();
            updateTitle();
        } catch (IOException e) {
            showError("Could not open file: " + e.getMessage());
        }
    }

    private void saveFile(boolean forceDialog) {
        if (currentFile == null || forceDialog) {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Text File");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            var file = chooser.showSaveDialog(stage);
            if (file == null) return;
            currentFile = file.toPath();
        }

        try {
            Files.writeString(currentFile, textArea.getText());
            updateTitle();
        } catch (IOException e) {
            showError("Could not save file: " + e.getMessage());
        }
    }

    private void updateTitle() {
        String fileName = currentFile != null ? currentFile.getFileName().toString() : "Untitled";
        stage.setTitle(fileName + " - JFX Notepad");
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JFX Notepad");
        alert.setContentText("A simple JavaFX Notepad application.\nBuilt for the JavaFX Styling & Notepad exercise.");
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
