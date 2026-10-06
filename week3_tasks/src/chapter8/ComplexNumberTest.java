package chapter8;

public class ComplexNumberTest {

    public static void main(String[] args) {
        ComplexNumber<Integer> c1 = new ComplexNumber<>(3, 5);
        ComplexNumber<Integer> c2 = new ComplexNumber<>(2, -4);

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c1 + c2 = " + ComplexNumber.add(c1, c2));
        System.out.println("c1 - c2 = " + ComplexNumber.subtract(c1, c2));
        System.out.println("c1 * c2 = " + ComplexNumber.multiply(c1, c2));
        System.out.println("c1 / c2 = " + ComplexNumber.divide(c1, c2));
    }
}
