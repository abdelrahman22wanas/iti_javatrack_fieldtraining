import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int DEFAULT_PORT = 5000;
    private static final Set<ClientHandler> CLIENTS = ConcurrentHashMap.newKeySet();
    private static final ExecutorService CLIENT_POOL = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int port = parsePort(args);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                CLIENT_POOL.shutdownNow();
                closeClients();
            }));

            System.out.println("Chat server listening on port " + port);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                CLIENTS.add(client);
                CLIENT_POOL.execute(client);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            CLIENT_POOL.shutdownNow();
            closeClients();
        }
    }

    private static int parsePort(String[] args) {
        if (args.length == 0) {
            return DEFAULT_PORT;
        }

        try {
            int port = Integer.parseInt(args[0]);
            if (port < 1 || port > 65535) {
                throw new NumberFormatException();
            }
            return port;
        } catch (NumberFormatException e) {
            System.err.println("Invalid port. Using " + DEFAULT_PORT + ".");
            return DEFAULT_PORT;
        }
    }

    private static void broadcast(String message) {
        for (ClientHandler client : CLIENTS) {
            client.send(message);
        }
    }

    private static String connectedUsers() {
        StringBuilder users = new StringBuilder("Connected users: ");
        boolean first = true;
        for (ClientHandler client : CLIENTS) {
            if (!first) {
                users.append(", ");
            }
            users.append(client.username);
            first = false;
        }
        return users.toString();
    }

    private static void closeClients() {
        for (ClientHandler client : CLIENTS) {
            client.close();
        }
        CLIENTS.clear();
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter output;
        private String username = "Anonymous";

        private ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
            ) {
                output = writer;
                String requestedName = input.readLine();
                if (requestedName != null && !requestedName.trim().isEmpty()) {
                    username = requestedName.trim();
                }

                send("Welcome, " + username + "! Commands: /users, /quit");
                broadcast("* " + username + " joined the chat.");

                String message;
                while ((message = input.readLine()) != null) {
                    message = message.trim();
                    if (message.isEmpty()) {
                        continue;
                    }
                    if ("/quit".equalsIgnoreCase(message)) {
                        break;
                    }
                    if ("/users".equalsIgnoreCase(message)) {
                        send(connectedUsers());
                    } else {
                        broadcast(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Connection error for " + username + ": " + e.getMessage());
            } finally {
                CLIENTS.remove(this);
                close();
                broadcast("* " + username + " left the chat.");
            }
        }

        private synchronized void send(String message) {
            if (output != null) {
                output.println(message);
            }
        }

        private void close() {
            try {
                socket.close();
            } catch (IOException ignored) {
                // The connection is already being closed.
            }
        }
    }
}
