package chapter8;

public class AlphabetCheckerTest {

    public static void main(String[] args) {
        String[] samples = {"HelloWorld", "Hello World", "Hello123", ""};

        for (String sample : samples) {
            System.out.println("\"" + sample + "\" contains only alphabets: "
                    + AlphabetChecker.containsOnlyAlphabets(sample));
        }
    }
}
