package chapter8;

import java.util.function.BiPredicate;

public class StringUtils {

    public static String betterString(String string1, String string2, BiPredicate<String, String> isFirstBetter) {
        return isFirstBetter.test(string1, string2) ? string1 : string2;
    }
}
