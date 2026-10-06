package chapter8;

public class ComplexNumber<T extends Number> {

    private final T real;
    private final T imaginary;

    public ComplexNumber(T real, T imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public T getImaginary() {
        return imaginary;
    }

    public static <T extends Number> ComplexNumber<Double> add(ComplexNumber<T> a, ComplexNumber<T> b) {
        double real = a.real.doubleValue() + b.real.doubleValue();
        double imaginary = a.imaginary.doubleValue() + b.imaginary.doubleValue();
        return new ComplexNumber<>(real, imaginary);
    }

    public static <T extends Number> ComplexNumber<Double> subtract(ComplexNumber<T> a, ComplexNumber<T> b) {
        double real = a.real.doubleValue() - b.real.doubleValue();
        double imaginary = a.imaginary.doubleValue() - b.imaginary.doubleValue();
        return new ComplexNumber<>(real, imaginary);
    }

    public static <T extends Number> ComplexNumber<Double> multiply(ComplexNumber<T> a, ComplexNumber<T> b) {
        double real = a.real.doubleValue() * b.real.doubleValue() - a.imaginary.doubleValue() * b.imaginary.doubleValue();
        double imaginary = a.real.doubleValue() * b.imaginary.doubleValue() + a.imaginary.doubleValue() * b.real.doubleValue();
        return new ComplexNumber<>(real, imaginary);
    }

    public static <T extends Number> ComplexNumber<Double> divide(ComplexNumber<T> a, ComplexNumber<T> b) {
        double denominator = b.real.doubleValue() * b.real.doubleValue() + b.imaginary.doubleValue() * b.imaginary.doubleValue();
        double real = (a.real.doubleValue() * b.real.doubleValue() + a.imaginary.doubleValue() * b.imaginary.doubleValue()) / denominator;
        double imaginary = (a.imaginary.doubleValue() * b.real.doubleValue() - a.real.doubleValue() * b.imaginary.doubleValue()) / denominator;
        return new ComplexNumber<>(real, imaginary);
    }

    @Override
    public String toString() {
        double im = imaginary.doubleValue();
        return real + (im >= 0 ? " + " : " - ") + Math.abs(im) + "i";
    }
}
