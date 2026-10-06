package Ch2;

import java.util.Scanner;

public class RepeatStringByNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter a string: ");
        String text = scanner.nextLine();

        for (int i = 0; i < number; i++) {
            System.out.println(text);
        }

        scanner.close();
    }
}
