package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Q4 - The remote business interface exposed by the calculator server.
 */
public interface CalculatorRemote extends Remote {
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException, ArithmeticException;
}
