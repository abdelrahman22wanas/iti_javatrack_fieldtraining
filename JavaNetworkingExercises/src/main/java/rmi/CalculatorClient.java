package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Q4 - Remote Calculator Client.
 * Usage: java rmi.CalculatorClient 30 + 40
 * (On some shells, quote the operator, e.g. "*", so it is not expanded
 * as a filename wildcard.)
 */
public class CalculatorClient {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java CalculatorClient <operand1> <operator> <operand2>");
            System.out.println("Example: java CalculatorClient 30 + 40");
            return;
        }

        try {
            double operand1 = Double.parseDouble(args[0]);
            String operator = args[1];
            double operand2 = Double.parseDouble(args[2]);

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            CalculatorRemote calculator = (CalculatorRemote) registry.lookup("CalculatorService");

            double result;
            switch (operator) {
                case "+":
                    result = calculator.add(operand1, operand2);
                    break;
                case "-":
                    result = calculator.subtract(operand1, operand2);
                    break;
                case "*":
                    result = calculator.multiply(operand1, operand2);
                    break;
                case "/":
                    result = calculator.divide(operand1, operand2);
                    break;
                default:
                    System.out.println("Unsupported operator: " + operator);
                    return;
            }

            System.out.println(formatNumber(operand1) + " " + operator + " "
                    + formatNumber(operand2) + " = " + formatNumber(result));

        } catch (ArithmeticException ae) {
            System.out.println("Error: " + ae.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Error: operands must be numbers.");
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }

    /** Prints whole numbers without a trailing ".0" (e.g. 70 instead of 70.0). */
    private static String formatNumber(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
