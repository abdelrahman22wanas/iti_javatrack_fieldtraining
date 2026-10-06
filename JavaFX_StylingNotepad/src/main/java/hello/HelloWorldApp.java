package hello;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Task 1 - "Hello World" JavaFX app.
 * All visual styling (font, gradient fill, reflection, drop shadow,
 * background) lives in hello.css and is applied only through style
 * classes/ids — nothing is hard-coded here.
 */
public class HelloWorldApp extends Application {

    @Override
    public void start(Stage stage) {
        Label helloLabel = new Label("Hello World");
        helloLabel.setId("hello-label");

        StackPane root = new StackPane(helloLabel);
        root.setAlignment(Pos.CENTER);
        root.setId("root-pane");

        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/hello/hello.css").toExternalForm());

        stage.setTitle("Hello World - JavaFX Effects & CSS");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
