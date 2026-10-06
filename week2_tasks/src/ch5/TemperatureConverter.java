package ch5;

import java.util.Scanner;
import java.util.function.Function;


public class TemperatureConverter {

    public static void main(String[] args) {
        Function<Double, Double> centigradeToFahrenheit = c -> (c * 9 / 5) + 32;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter temperature in Centigrade: ");
        System.out.flush();
        double centigrade = scanner.nextDouble();

        double fahrenheit = centigradeToFahrenheit.apply(centigrade);

        System.out.println(centigrade + " C = " + fahrenheit + " F");

        scanner.close();
    }
}
