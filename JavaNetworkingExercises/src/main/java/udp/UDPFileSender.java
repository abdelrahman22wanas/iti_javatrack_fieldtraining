package udp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Q2 - UDP File Sender.
 * Reads a text file line by line and sends each line as a separate
 * UDP datagram, waiting 200 ms between lines. Sends an "EOF" marker
 * at the end so the receiver knows the transfer finished.
 */
public class UDPFileSender {

    private static final String RECEIVER_HOST = "localhost";
    private static final int RECEIVER_PORT = 6000;
    private static final int DELAY_MS = 200;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Default sample file bundled under src/main/resources/message.txt
        String filePath = args.length > 0 ? args[0] : "src/main/resources/message.txt";

        try (DatagramSocket socket = new DatagramSocket();
             BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {

            InetAddress receiverAddress = InetAddress.getByName(RECEIVER_HOST);
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                byte[] data = line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(data, data.length, receiverAddress, RECEIVER_PORT);
                socket.send(packet);
                System.out.println("Sent line " + lineNumber + ": " + line);
                Thread.sleep(DELAY_MS);
            }

            // Optional end-of-file marker.
            byte[] eof = "EOF".getBytes(StandardCharsets.UTF_8);
            socket.send(new DatagramPacket(eof, eof.length, receiverAddress, RECEIVER_PORT));
            System.out.println("File transfer complete.");
        }
    }
}
