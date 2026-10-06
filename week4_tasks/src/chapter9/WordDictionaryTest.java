package chapter9;

public class WordDictionaryTest {

    public static void main(String[] args) {
        String[] words = {
                "Banana", "Apple", "Cherry", "Avocado", "Blueberry",
                "Mango", "Melon", "Apricot", "Cantaloupe", "Kiwi"
        };

        WordDictionary dictionary = new WordDictionary();
        dictionary.addWords(words);

        System.out.println("All letters and their words:");
        dictionary.printAllLettersAndWords();

        System.out.println();
        System.out.println("Words starting with 'A':");
        dictionary.printWordsForLetter('A');

        System.out.println("Words starting with 'M':");
        dictionary.printWordsForLetter('M');

        System.out.println("Words starting with 'Z':");
        dictionary.printWordsForLetter('Z');
    }
}
