package http;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Q3 - Uses URL / URLConnection to read the HTTP header fields and HTML
 * content of a page, then displays the header in a TextArea and the
 * page content inside a JavaFX WebView.
 */
public class HttpHeaderViewerApp {

    private TextField urlField;
    private TextArea headerArea;
    private WebView webView;

    private void buildUI(Stage stage) {
        urlField = new TextField("https://www.example.com");
        headerArea = new TextArea();
        webView = new WebView();

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> loadPage(urlField.getText().trim()));

        HBox topBar = new HBox(10, new Label("URL:"), urlField, loadButton);
        topBar.setPadding(new Insets(10));
        HBox.setHgrow(urlField, Priority.ALWAYS);

        headerArea.setEditable(false);
        headerArea.setPrefRowCount(8);

        VBox root = new VBox(10, topBar, new Label("HTTP Header:"), headerArea,
                new Label("Page Content:"), webView);
        VBox.setVgrow(webView, Priority.ALWAYS);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("HTTP Header & Page Content Viewer");
        stage.setScene(scene);
        stage.setOnHidden(e -> Platform.exit());
        stage.show();

        // Load the default URL on startup.
        loadPage(urlField.getText());
    }

    private void loadPage(String urlText) {
        headerArea.clear();
        try {
            URL url = new URL(urlText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            StringBuilder headerInfo = new StringBuilder();
            headerInfo.append("Status: ")
                    .append(connection.getResponseCode())
                    .append(" ")
                    .append(connection.getResponseMessage())
                    .append("\n");
            headerInfo.append("Content-Type: ").append(connection.getContentType()).append("\n");
            headerInfo.append("Content-Length: ").append(connection.getContentLengthLong()).append("\n");
            headerInfo.append("Server: ").append(connection.getHeaderField("Server")).append("\n");

            long dateHeader = connection.getDate();
            String dateStr = dateHeader > 0
                    ? new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(dateHeader))
                    : connection.getHeaderField("Date");
            headerInfo.append("Date: ").append(dateStr).append("\n");

            headerArea.setText(headerInfo.toString());

            // Read the HTML body.
            StringBuilder html = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    html.append(line).append("\n");
                }
            }

            webView.getEngine().loadContent(html.toString());
            connection.disconnect();

        } catch (Exception ex) {
            headerArea.setText("Error loading URL: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Platform.startup(() -> {
            HttpHeaderViewerApp app = new HttpHeaderViewerApp();
            app.buildUI(new Stage());
        });
    }
}
