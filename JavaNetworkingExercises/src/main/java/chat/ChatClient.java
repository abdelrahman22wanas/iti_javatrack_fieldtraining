package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Q1 - Simple chat client. Connects to ChatServer, sends typed lines,
 * and prints every message the server broadcasts.
 *
 * Run several instances (e.g. from separate IntelliJ run configurations
 * or separate terminals) to simulate multiple clients like Ali, Sara, Omar.
 */
public class ChatClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send our name first, so the server can label our messages.
        out.println(name);

        // Background thread: continuously print whatever the server sends us.
        Thread listener = new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        });
        listener.setDaemon(true);
        listener.start();

        // Main thread: read what the user types and send it to the server.
        String userInput;
        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("/quit")) break;
            out.println(userInput);
        }

        socket.close();
    }
}
