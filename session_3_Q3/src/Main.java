import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Rider> riders = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File("src/riders.txt"));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] data = line.split(",");
                try {
                    String name = data[0];
                    int height = Integer.parseInt(data[1]);
                    if (height < 140)
                        throw new UnsafeHeightException("Unsafe rider: " + name);
                    riders.add(new Rider(name, height));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid height for " + data[0]);
                } catch (UnsafeHeightException e) {
                    System.out.println(e.getMessage());
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println("\nValid Riders:");
        for (Rider r : riders) {
            System.out.println(r);
        }
    }
}