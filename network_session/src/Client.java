import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? parsePort(args[1]) : DEFAULT_PORT;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Enter your username: ");
            String username = console.readLine();
            if (username == null || username.trim().isEmpty()) {
                username = "Anonymous";
            }
            serverOutput.println(username.trim());

            BufferedReader finalServerInput = serverInput;
            Thread receiver = new Thread(() -> receiveMessages(finalServerInput), "chat-receiver");
            receiver.setDaemon(true);
            receiver.start();

            System.out.println("Connected. Type messages, /users, or /quit.");
            String message;
            while ((message = console.readLine()) != null) {
                serverOutput.println(message);
                if ("/quit".equalsIgnoreCase(message.trim())) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to connect: " + e.getMessage());
        }
    }

    private static void receiveMessages(BufferedReader serverInput) {
        try {
            String message;
            while ((message = serverInput.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Disconnected from server.");
        }
    }

    private static int parsePort(String value) {
        try {
            int port = Integer.parseInt(value);
            if (port < 1 || port > 65535) {
                throw new NumberFormatException();
            }
            return port;
        } catch (NumberFormatException e) {
            System.err.println("Invalid port. Using " + DEFAULT_PORT + ".");
            return DEFAULT_PORT;
        }
    }
}
