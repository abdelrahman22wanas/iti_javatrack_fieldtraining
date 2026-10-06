package udp;

import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Q2 - UDP File Receiver.
 * Listens on the agreed port and prints each received line as it arrives.
 * Start this BEFORE running UDPFileSender.
 */
public class UDPFileReceiver {

    private static final int PORT = 6000;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Receiver listening on port " + PORT + " ...");
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);

                if (received.equals("EOF")) {
                    System.out.println("End of file reached. Transfer complete.");
                    break;
                }
                System.out.println(received);
            }
        }
    }
}
