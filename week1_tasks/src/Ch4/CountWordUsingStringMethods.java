package Ch4;

public class CountWordUsingStringMethods {

    public static int countUsingSplit(String sentence, String word) {
        String[] tokens = sentence.split(" ");
        int count = 0;
        for (String token : tokens) {
            if (token.equals(word)) {
                count++;
            }
        }
        return count;
    }

    public static int countUsingIndexOf(String sentence, String word) {
        int count = 0;
        int index = 0;

        while ((index = sentence.indexOf(word, index)) != -1) {
            boolean startOk = (index == 0) || (sentence.charAt(index - 1) == ' ');
            int endPos = index + word.length();
            boolean endOk = (endPos == sentence.length()) || (sentence.charAt(endPos) == ' ');

            if (startOk && endOk) {
                count++;
            }
            index += word.length();
        }
        return count;
    }

    public static void main(String[] args) {
        String sentence = "the quick brown fox jumps over the lazy dog the fox ran";
        String word = "the";

        System.out.println("Sentence: " + sentence);
        System.out.println("Word: " + word);
        System.out.println("Count using split(): " + countUsingSplit(sentence, word));
        System.out.println("Count using indexOf(): " + countUsingIndexOf(sentence, word));
    }
}
