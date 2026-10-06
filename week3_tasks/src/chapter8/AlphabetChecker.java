package chapter8;

public class AlphabetChecker {

    public static boolean containsOnlyAlphabets(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
