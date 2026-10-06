package listview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * VID12 - Customized ListView.
 * <p>
 * Instead of the default single-line text row, each Contact is rendered
 * as: a circular "avatar" showing the contact's initial, a two-line
 * name/email block, and a colored status dot — all built by overriding
 * {@link #updateItem(Contact, boolean)} and returning a custom layout
 * via {@link #setGraphic(javafx.scene.Node)}.
 */
public class ContactListCell extends ListCell<Contact> {

    private final HBox root = new HBox(10);
    private final Circle avatar = new Circle(18);
    private final Label initialLabel = new Label();
    private final Label nameLabel = new Label();
    private final Label emailLabel = new Label();
    private final Circle statusDot = new Circle(5);
    private final VBox textBox = new VBox(2);

    public ContactListCell() {
        // Avatar: a colored circle with the contact's first initial on top of it.
        javafx.scene.layout.StackPane avatarStack = new javafx.scene.layout.StackPane(avatar, initialLabel);
        initialLabel.setTextFill(Color.WHITE);
        initialLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 13));
        emailLabel.setTextFill(Color.GRAY);
        emailLabel.setFont(Font.font(11));

        textBox.getChildren().addAll(nameLabel, emailLabel);
        HBox.setHgrow(textBox, Priority.ALWAYS);

        root.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(6, 10, 6, 10));
        root.getChildren().addAll(avatarStack, textBox, statusDot);
        HBox.setMargin(statusDot, new Insets(0, 4, 0, 0));
    }

    @Override
    protected void updateItem(Contact contact, boolean empty) {
        super.updateItem(contact, empty);

        if (empty || contact == null) {
            setGraphic(null);
            setText(null);
            return;
        }

        nameLabel.setText(contact.getName());
        emailLabel.setText(contact.getEmail());
        initialLabel.setText(contact.getName().substring(0, 1).toUpperCase());
        avatar.setFill(colorForName(contact.getName()));
        statusDot.setFill(colorForStatus(contact.getStatus()));

        setGraphic(root);
        setText(null); // we render everything via the graphic, not the default text
    }

    /** Deterministic pastel color per name, so each avatar looks distinct but stable. */
    private Color colorForName(String name) {
        int hue = Math.abs(name.hashCode()) % 360;
        return Color.hsb(hue, 0.55, 0.75);
    }

    private Color colorForStatus(String status) {
        return switch (status) {
            case "Online" -> Color.LIMEGREEN;
            case "Away" -> Color.ORANGE;
            default -> Color.LIGHTGRAY; // Offline
        };
    }
}
