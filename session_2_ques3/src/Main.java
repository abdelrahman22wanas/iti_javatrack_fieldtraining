import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
@FunctionalInterface
interface operation {
    int apply(int a, int b);
}
public class Main {
        public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int a = input.nextInt();
        System.out.print("Enter the second number: ");
        int b = input.nextInt();
        operation add = (x, y) -> x + y;
        operation subtract = (x, y) -> x - y;
        operation multiply = (x, y) -> x * y;
        System.out.println("Addition: " + add.apply(a, b));
        System.out.println("Subtraction: " + subtract.apply(a, b));
        System.out.println("Multiplication: " + multiply.apply(a, b));
        operation OP =(x,y) -> x+y;

        operation calculation = (x, y) -> (x+y) + OP.apply(x,y);
            System.out.println("calculation: "+ calculation.apply(a, b));
            BinaryOperator<Integer> addition = (x, y) -> x + y;

            System.out.println("Addition: " + add.apply(a, b));        }

    }
