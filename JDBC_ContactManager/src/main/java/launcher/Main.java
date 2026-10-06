package launcher;

import app.BatchAndRowSetDemo;
import app.CrudDemo;
import gui.ContactViewerGUI;

import java.util.Scanner;

/**
 * Single entry point for all three JDBC requirements.
 *
 * <pre>
 *   mvn exec:java                   -> interactive menu
 *   java launcher.Main [option]
 *     1 -> app.CrudDemo            (Requirement 1 - console CRUD)
 *     2 -> gui.ContactViewerGUI    (Requirement 2 - Swing GUI)
 *     3 -> app.BatchAndRowSetDemo  (Requirement 3 - batch + RowSets)
 * </pre>
 *
 * <p>Passing no argument (or an invalid one) opens the menu. Remember to run
 * {@code sql/schema.sql} and check the connection details described in the
 * README before running any exercise. Each exercise class still has its own
 * {@code main} method and can be run directly from your IDE.</p>
 */
public class Main {

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 3;

    public static void main(String[] args) {
        int choice = readChoice(args);
        try {
            switch (choice) {
                case 1 -> CrudDemo.main(args);
                case 2 -> ContactViewerGUI.main(args);
                case 3 -> BatchAndRowSetDemo.main(args);
                default -> printUsage();
            }
        } catch (Exception e) {
            System.err.println("Exercise terminated with an error: " + e.getMessage());
        }
    }

    private static int readChoice(String[] args) {
        if (args.length > 0) {
            try {
                int choice = Integer.parseInt(args[0]);
                if (choice >= MIN_OPTION && choice <= MAX_OPTION) {
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
                if (choice >= MIN_OPTION && choice <= MAX_OPTION) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
                // re-prompt below
            }
            System.out.println("Please enter a number between 1 and " + MAX_OPTION + ".\n");
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("JDBC Contact Manager - which requirement do you want to run?");
        System.out.println("  1) CRUD Demo (Requirement 1 - console)");
        System.out.println("  2) Contact Viewer GUI (Requirement 2 - Swing)");
        System.out.println("  3) Batch & RowSet Demo (Requirement 3)");
        System.out.println();
    }

    private static void printUsage() {
        printMenu();
        System.out.println("Usage: java launcher.Main <1.." + MAX_OPTION + ">   (or no argument for the interactive menu)");
    }
}