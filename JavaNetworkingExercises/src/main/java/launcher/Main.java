package launcher;

import chat.ChatClient;
import chat.ChatServer;
import http.HttpHeaderViewerApp;
import rmi.CalculatorClient;
import rmi.CalculatorServer;
import udp.UDPFileReceiver;
import udp.UDPFileSender;

import java.util.Scanner;

/**
 * Single entry point for all four networking exercises (Q1-Q4).
 *
 * <pre>
 *   mvn exec:java                   -> interactive menu
 *   java launcher.Main [option]
 *     1 -> chat.ChatServer            (Q1 server - run first)
 *     2 -> chat.ChatClient            (Q1 client - run several terminals)
 *     3 -> udp.UDPFileReceiver        (Q2 receiver - run first)
 *     4 -> udp.UDPFileSender          (Q2 sender)
 *     5 -> http.HttpHeaderViewerApp   (Q3 - JavaFX)
 *     6 -> rmi.CalculatorServer       (Q4 RMI server - run first)
 *     7 -> rmi.CalculatorClient       (Q4 client - enter e.g. "30 + 40")
 * </pre>
 *
 * <p>Passing no argument (or an invalid one) opens the menu. Servers and the
 * JavaFX app block until stopped, so run those on their own terminal. Every
 * exercise class still has its own {@code main} method and can be run
 * directly from your IDE.</p>
 */
public class Main {

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 7;

    public static void main(String[] args) {
        int choice = readChoice(args);
        try {
            switch (choice) {
                case 1 -> ChatServer.main(args);
                case 2 -> ChatClient.main(args);
                case 3 -> UDPFileReceiver.main(args);
                case 4 -> UDPFileSender.main(args);
                case 5 -> HttpHeaderViewerApp.main(args);
                case 6 -> CalculatorServer.main(args);
                case 7 -> runCalculatorClient(args);
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

    /**
     * Offline calculator client needs 3 values (operand, operator, operand).
     * Accept them as program arguments ("launcher.Main 7 30 + 40") or prompt.
     */
    private static void runCalculatorClient(String[] args) {
        if (args.length == 4) {
            CalculatorClient.main(new String[]{args[1], args[2], args[3]});
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression (e.g. 30 + 40): ");
        String line = scanner.nextLine();
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            System.out.println("Expected 3 values: <operand1> <operator> <operand2>");
            return;
        }
        CalculatorClient.main(parts);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Java Networking Exercises - which one do you want to run?");
        System.out.println("  1) Chat Server (Q1 - run first)");
        System.out.println("  2) Chat Client (Q1 - run several)");
        System.out.println("  3) UDP File Receiver (Q2 - run first)");
        System.out.println("  4) UDP File Sender (Q2)");
        System.out.println("  5) HTTP Header Viewer (Q3 - JavaFX)");
        System.out.println("  6) RMI Calculator Server (Q4 - run first)");
        System.out.println("  7) RMI Calculator Client (Q4 - enter an expression)");
        System.out.println();
    }

    private static void printUsage() {
        printMenu();
        System.out.println("Usage: java launcher.Main <1.." + MAX_OPTION + ">   (or no argument for the interactive menu)");
    }
}