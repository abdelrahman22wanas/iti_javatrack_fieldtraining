package Ch4;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountWordUsingRegex {

    public static int countWord(String sentence, String word) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(sentence);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static int countAllWords(String sentence) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(sentence);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps over the lazy dog the fox ran";
        String word = "the";

        System.out.println("Sentence: " + sentence);
        System.out.println("Word: " + word);
        System.out.println("Count using regex: " + countWord(sentence, word));
        System.out.println("Total number of words in sentence: " + countAllWords(sentence));
    }
}
