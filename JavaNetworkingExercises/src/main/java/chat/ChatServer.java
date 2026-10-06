package chat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Q1 - Multi-Client Chat Room Server.
 * Accepts many clients at once (one thread per client) and broadcasts
 * every received message to all connected clients.
 */
public class ChatServer {

    private static final int PORT = 5000;

    // Thread-safe list of every connected client's writer, keyed by name.
    private static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        System.out.println("Chat server starting on port " + PORT + " ...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    /** Sends a line of text to every currently connected client. */
    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            client.send(message);
        }
    }

    static void removeClient(ClientHandler handler) {
        clients.remove(handler);
        broadcast(handler.getName() + " has left the chat.", handler);
        System.out.println(handler.getName() + " disconnected.");
    }

    /** Handles a single client connection on its own thread. */
    static class ClientHandler implements Runnable {
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String name = "Unknown";

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        String getName() {
            return name;
        }

        void send(String message) {
            out.println(message);
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // First line from the client is treated as their display name.
                name = in.readLine();
                if (name == null) name = "Guest";
                System.out.println(name + " connected.");
                broadcast(name + " has joined the chat.", this);

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(name + ": " + line);
                    broadcast(name + ": " + line, this);
                }
            } catch (IOException e) {
                System.out.println(name + " connection lost: " + e.getMessage());
            } finally {
                removeClient(this);
                try { socket.close(); } catch (IOException ignored) {}
            }
        }
    }
}
