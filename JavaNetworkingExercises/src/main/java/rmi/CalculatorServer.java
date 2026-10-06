package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Q4 - Starts an RMI registry and binds the CalculatorImpl remote object
 * under the name "CalculatorService". Run this BEFORE CalculatorClient.
 */
public class CalculatorServer {

    private static final int RMI_PORT = 1099;

    public static void main(String[] args) {
        try {
            CalculatorImpl calculator = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(RMI_PORT);
            registry.rebind("CalculatorService", calculator);
            System.out.println("Calculator RMI server ready on port " + RMI_PORT + ".");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
