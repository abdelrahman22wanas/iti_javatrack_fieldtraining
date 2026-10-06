package treeview;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * VID16 - TreeView (Demo).
 * <p>
 * Builds a small company org-chart as a hierarchical TreeView: a root
 * ("Company"), department nodes, and employee leaf nodes. Departments and
 * employees get different icons via a custom graphic per TreeItem, and
 * selecting any node updates a label showing its full path.
 * <p>
 * Each node now has a CheckBox: checking a department checks all its
 * employees; unchecking a department unchecks all its employees.
 * <p>
 * The root layout is a {@link StackPane} with each child pinned to the
 * top / bottom so the title, tree and status label stack vertically.
 */
public class TreeViewDemo extends Application {

    @Override
    public void start(Stage stage) {
        CheckableTreeItem root = new CheckableTreeItem("Company", folderIcon());
        root.setExpanded(true);

        root.getChildren().addAll(
                department("Engineering", "Ali Hassan", "Sara Mostafa", "Omar Khaled"),
                department("Marketing", "Mona Adel", "Youssef Tarek"),
                department("Human Resources", "Nour Ibrahim")
        );

        TreeView<String> treeView = new TreeView<>(root);
        treeView.setShowRoot(true);
        treeView.setCellFactory(tv -> new CheckBoxTreeCell());

        Label selectedPathLabel = new Label("Select an item to see its full path.");
        selectedPathLabel.setStyle("-fx-font-style: italic;");

        treeView.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                selectedPathLabel.setText("Selected: " + fullPath(newItem));
            }
        });

        Label title = new Label("Company Org Chart");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        StackPane rootPane = new StackPane(title, treeView, selectedPathLabel);
        rootPane.setPadding(new Insets(15));
        StackPane.setAlignment(title, Pos.TOP_LEFT);
        StackPane.setMargin(title, new Insets(0, 0, 10, 0));
        StackPane.setAlignment(treeView, Pos.TOP_LEFT);
        treeView.setMaxWidth(Double.MAX_VALUE);
        treeView.setMaxHeight(Double.MAX_VALUE);
        StackPane.setMargin(treeView, new Insets(35, 0, 0, 0));
        StackPane.setAlignment(selectedPathLabel, Pos.BOTTOM_LEFT);
        StackPane.setMargin(selectedPathLabel, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(rootPane, 420, 440);
        stage.setTitle("TreeView Demo");
        stage.setScene(scene);
        stage.show();
    }

    /** Builds a department CheckableTreeItem containing one leaf CheckableTreeItem per employee name. */
    private CheckableTreeItem department(String name, String... employees) {
        CheckableTreeItem deptItem = new CheckableTreeItem(name, folderIcon());
        for (String employee : employees) {
            deptItem.getChildren().add(new CheckableTreeItem(employee, personIcon()));
        }
        return deptItem;
    }

    /** Walks up the parent chain to build a "Company > Engineering > Ali Hassan" style path. */
    private String fullPath(TreeItem<String> item) {
        StringBuilder path = new StringBuilder(item.getValue());
        TreeItem<String> parent = item.getParent();
        while (parent != null) {
            path.insert(0, parent.getValue() + " > ");
            parent = parent.getParent();
        }
        return path.toString();
    }

    // ── Checkbox tree cell with parent/child propagation ──────────────────

    /**
     * Custom TreeCell that prepends a CheckBox to every row.
     * Checking a department ticks all its employees and vice-versa.
     */
    private class CheckBoxTreeCell extends TreeCell<String> {
        private final CheckBox checkBox = new CheckBox();

        private CheckBoxTreeCell() {
            checkBox.setOnAction(e -> {
                CheckableTreeItem item = (CheckableTreeItem) getTreeItem();
                if (item != null) {
                    item.setChecked(checkBox.isSelected());
                    propagateToChildren(item, checkBox.isSelected());
                    updateParentState(item);
                }
            });
        }

        @Override
        protected void updateItem(String value, boolean empty) {
            super.updateItem(value, empty);
            if (empty || value == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(value);
                TreeItem<String> treeItem = getTreeItem();
                if (treeItem instanceof CheckableTreeItem checkable) {
                    checkBox.setSelected(checkable.isChecked());
                    // visually distinguish folders from leaves
                    Node icon = treeItem.isLeaf() ? personIcon() : folderIcon();
                    setGraphic(new HBox(4, checkBox, icon));
                } else {
                    setGraphic(checkBox);
                }
            }
        }
    }

    /** Recursively set all descendants to the given selected state. */
    private void propagateToChildren(CheckableTreeItem item, boolean selected) {
        for (TreeItem<String> child : item.getChildren()) {
            ((CheckableTreeItem) child).setChecked(selected);
            propagateToChildren((CheckableTreeItem) child, selected);
        }
    }

    /** If every child is selected, select this node; otherwise deselect it. */
    private void updateParentState(CheckableTreeItem item) {
        TreeItem<String> parentItem = item.getParent();
        if (parentItem == null || !(parentItem instanceof CheckableTreeItem)) {
            return;
        }
        CheckableTreeItem parent = (CheckableTreeItem) parentItem;
        boolean allSelected = parent.getChildren().stream()
                .map(CheckableTreeItem.class::cast)
                .allMatch(CheckableTreeItem::isChecked);
        parent.setChecked(allSelected);
        updateParentState(parent);
    }

    /**
     * TreeItem that carries its own checked state for the checkbox UI.
     * Kept as a nested class so the checkbox behaviour stays version-independent.
     */
    private class CheckableTreeItem extends TreeItem<String> {
        private final BooleanProperty checked = new SimpleBooleanProperty(false);

        CheckableTreeItem(String value) {
            super(value);
        }

        CheckableTreeItem(String value, Node graphic) {
            super(value, graphic);
        }

        boolean isChecked() { return checked.get(); }

        void setChecked(boolean value) { checked.set(value); }
    }

    private Rectangle folderIcon() {
        Rectangle rectangle = new Rectangle(14, 12);
        rectangle.setArcWidth(3);
        rectangle.setArcHeight(3);
        rectangle.setFill(Color.web("#f4b400"));
        return rectangle;
    }

    private Circle personIcon() {
        Circle circle = new Circle(6);
        circle.setFill(Color.web("#4285f4"));
        return circle;
    }

    public static void main(String[] args) {
        launch(args);
    }
}