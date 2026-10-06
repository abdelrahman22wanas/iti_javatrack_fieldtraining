import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  java Main server [port]");
            System.out.println("  java Main client [host] [port]");
            return;
        }

        String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
        if ("server".equalsIgnoreCase(args[0])) {
            Server.main(commandArgs);
        } else if ("client".equalsIgnoreCase(args[0])) {
            Client.main(commandArgs);
        } else {
            System.err.println("Unknown mode: " + args[0]);
        }
    }
}
