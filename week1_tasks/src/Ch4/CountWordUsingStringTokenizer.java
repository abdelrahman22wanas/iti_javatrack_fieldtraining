package Ch4;

import java.util.StringTokenizer;

public class CountWordUsingStringTokenizer {

    public static int countWord(String sentence, String word) {
        StringTokenizer tokenizer = new StringTokenizer(sentence);
        int count = 0;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals(word)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps over the lazy dog the fox ran";
        String word = "the";

        System.out.println("Sentence: " + sentence);
        System.out.println("Word: " + word);
        System.out.println("Count using StringTokenizer: " + countWord(sentence, word));
    }
}
