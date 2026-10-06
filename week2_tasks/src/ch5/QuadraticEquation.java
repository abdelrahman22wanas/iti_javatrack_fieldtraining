package ch5;

import java.util.Scanner;
import java.util.function.Function;


public class QuadraticEquation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter coefficient a: ");
        System.out.flush();
        double a = scanner.nextDouble();
        System.out.print("Enter coefficient b: ");
        System.out.flush();
        double b = scanner.nextDouble();
        System.out.print("Enter coefficient c: ");
        System.out.flush();
        double c = scanner.nextDouble();
        scanner.close();

        Function<double[], String> solveQuadratic = coeffs -> {
            double coA = coeffs[0];
            double coB = coeffs[1];
            double coC = coeffs[2];

            double discriminant = (coB * coB) - (4 * coA * coC);

            if (discriminant > 0) {
                double root1 = (-coB + Math.sqrt(discriminant)) / (2 * coA);
                double root2 = (-coB - Math.sqrt(discriminant)) / (2 * coA);
                return "Two real roots: x1 = " + root1 + " , x2 = " + root2;
            } else if (discriminant == 0) {
                double root = -coB / (2 * coA);
                return "One real root: x = " + root;
            } else {
                double realPart = -coB / (2 * coA);
                double imaginaryPart = Math.sqrt(-discriminant) / (2 * coA);
                return "Two complex roots: x1 = " + realPart + " + " + imaginaryPart + "i , x2 = "
                        + realPart + " - " + imaginaryPart + "i";
            }
        };

        String result = solveQuadratic.apply(new double[] { a, b, c });
        System.out.println(result);
    }
}
