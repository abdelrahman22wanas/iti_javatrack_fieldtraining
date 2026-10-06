package Ch2;

import java.util.Scanner;

public class CheckAndPrintInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a value: ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            System.out.println("You entered an empty value.");
        } else {
            System.out.println("You entered: " + input);
        }

        scanner.close();
    }
}
