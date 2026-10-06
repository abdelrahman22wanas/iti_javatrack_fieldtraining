import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       File uni=new File("Uni.txt");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name: ");
        String name = input.nextLine();
        System.out.println("enter the age: ");
        int age = input.nextInt();
        try (FileWriter writer = new FileWriter("Uni.txt");){
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative.");
            }
            String agestr = String.valueOf(age);
            writer.write(name + "," + agestr);

            System.out.println("Data written successfully.");

        }catch (IllegalArgumentException  e) {
            System.out.println(e.getMessage());
        }
           catch (IOException e) {
            e.printStackTrace();
           }
    }


}