package hello;

/**
 * Some IDE run configurations refuse to launch a class that directly
 * extends javafx.application.Application from the classpath. Running
 * this small Launcher instead (which does NOT extend Application) sidesteps
 * that restriction. Use this as the "main class" in IntelliJ if
 * HelloWorldApp still reports missing JavaFX runtime components.
 */
public class Launcher {
    public static void main(String[] args) {
        HelloWorldApp.main(args);
    }
}
