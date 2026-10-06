package chapter8;

public class StringUtilsTest {

    public static void main(String[] args) {
        String string1 = "hello";
        String string2 = "hi";

        String longer = StringUtils.betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
        System.out.println("Longer string: " + longer);

        String first = StringUtils.betterString(string1, string2, (s1, s2) -> true);
        System.out.println("Always first: " + first);
    }
}
