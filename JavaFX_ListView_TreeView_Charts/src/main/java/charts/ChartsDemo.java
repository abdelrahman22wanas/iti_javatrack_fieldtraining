package charts;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * VID18 - Charts (Demo).
 * <p>
 * javafx.scene.chart ships inside javafx-controls, so no extra dependency
 * is needed. Shows three of the most commonly used chart types, each on
 * its own tab: a BarChart (categorical comparison), a PieChart
 * (proportions), and a LineChart (a trend over time).
 */
public class ChartsDemo extends Application {

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                new Tab("Bar Chart", buildBarChart()),
                new Tab("Pie Chart", buildPieChart()),
                new Tab("Line Chart", buildLineChart())
        );
        tabPane.getTabs().forEach(tab -> tab.setClosable(false));

        Scene scene = new Scene(tabPane, 600, 450);
        stage.setTitle("Charts Demo");
        stage.setScene(scene);
        stage.show();
    }

    /** Quarterly sales comparison across two product lines. */
    private BarChart<String, Number> buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Quarter");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Sales (in $1000s)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Quarterly Sales by Product");

        XYChart.Series<String, Number> productA = new XYChart.Series<>();
        productA.setName("Product A");
        productA.getData().add(new XYChart.Data<>("Q1", 120));
        productA.getData().add(new XYChart.Data<>("Q2", 150));
        productA.getData().add(new XYChart.Data<>("Q3", 90));
        productA.getData().add(new XYChart.Data<>("Q4", 175));

        XYChart.Series<String, Number> productB = new XYChart.Series<>();
        productB.setName("Product B");
        productB.getData().add(new XYChart.Data<>("Q1", 80));
        productB.getData().add(new XYChart.Data<>("Q2", 95));
        productB.getData().add(new XYChart.Data<>("Q3", 130));
        productB.getData().add(new XYChart.Data<>("Q4", 110));

        barChart.getData().addAll(productA, productB);
        return barChart;
    }

    /** Market share breakdown. */
    private PieChart buildPieChart() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("Chrome", 63),
                new PieChart.Data("Safari", 19),
                new PieChart.Data("Edge", 5),
                new PieChart.Data("Firefox", 3),
                new PieChart.Data("Other", 10)
        );

        PieChart pieChart = new PieChart(data);
        pieChart.setTitle("Browser Market Share (%)");
        return pieChart;
    }

    /** A week's temperature trend. */
    private LineChart<String, Number> buildLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Day");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temperature (\u00b0C)");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Weekly Temperature Trend");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cairo");
        series.getData().add(new XYChart.Data<>("Mon", 28));
        series.getData().add(new XYChart.Data<>("Tue", 30));
        series.getData().add(new XYChart.Data<>("Wed", 27));
        series.getData().add(new XYChart.Data<>("Thu", 32));
        series.getData().add(new XYChart.Data<>("Fri", 31));
        series.getData().add(new XYChart.Data<>("Sat", 29));
        series.getData().add(new XYChart.Data<>("Sun", 33));

        lineChart.getData().add(series);
        return lineChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
