package ch6;


@Author(name = "Abdelrahman Wanas", date = "2026-07-27")
public class AuthoredClass {

    @Author(name = "Abdelrahman Wanas")
    private String message;

    @Author(name = "Abdelrahman Wanas", date = "2026-07-27")
    public AuthoredClass() {
        this.message = "Hello from AuthoredClass";
    }

    @Author(name = "Abdelrahman Wanas")
    public void printMessage() {
        System.out.println(message);
    }
}
