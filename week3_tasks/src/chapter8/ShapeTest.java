package chapter8;

import java.util.ArrayList;
import java.util.List;

public class ShapeTest {

    public static void drawAll(List<? extends Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public static void main(String[] args) {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle());
        rectangles.add(new Rectangle());

        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle());
        circles.add(new Circle());

        drawAll(rectangles);
        drawAll(circles);
    }
}
