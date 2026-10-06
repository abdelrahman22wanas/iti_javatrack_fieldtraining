package launcher;

import hello.HelloWorldApp;
import javafx.application.Application;
import notepad.NotepadApp;

import java.util.Scanner;

/**
 * Single entry point for both JavaFX exercises (Task 1 and Task 2).
 *
 * <pre>
 *   mvn javafx:run                  -> interactive menu (type 1 or 2)
 *   mvn exec:java                   -> interactive menu (type 1 or 2)
 *   java launcher.Main 1            -> runs hello.HelloWorldApp (Task 1)
 *   java launcher.Main 2            -> runs notepad.NotepadApp  (Task 2)
 * </pre>
 *
 * <p>Passing no argument (or an argument that is not 1/2) opens the menu.
 * Each application class still has its own {@code main} method and can be
 * run directly from your IDE.
 */
public class Main {

    public static void main(String[] args) {
        int choice = readChoice(args);

        Class<? extends Application> appClass;
        switch (choice) {
            case 1 -> appClass = HelloWorldApp.class;
            case 2 -> appClass = NotepadApp.class;
            default -> {
                printUsage();
                return;
            }
        }

        System.out.println("Launching " + appClass.getSimpleName() + " ...");
        Application.launch(appClass, new String[0]);
    }

    private static int readChoice(String[] args) {
        if (args.length > 0) {
            try {
                int choice = Integer.parseInt(args[0]);
                if (choice == 1 || choice == 2) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // fall through to the interactive menu
            }
        }

        Scanner scanner = new Scanner(System.in);
        printMenu();
        while (true) {
            System.out.print("Your choice: ");
            String line = scanner.nextLine();
            try {
                int choice = Integer.parseInt(line.trim());
                if (choice == 1 || choice == 2) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // re-prompt below
            }
            System.out.println("Please enter 1 or 2.\n");
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("JavaFX Styling & Notepad - which exercise do you want to run?");
        System.out.println("  1) HelloWorldApp (effects & CSS)");
        System.out.println("  2) NotepadApp (menus + text area)");
        System.out.println();
    }

    private static void printUsage() {
        printMenu();
        System.out.println("Usage: java launcher.Main <1|2>   (or no argument for the interactive menu)");
    }
}