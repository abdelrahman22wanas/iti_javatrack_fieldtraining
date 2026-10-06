package chapter9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class    WordDictionary {

    private final Map<Character, List<String>> wordsByLetter = new TreeMap<>();

    public void addWords(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }

    public void addWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        char key = Character.toUpperCase(word.charAt(0));
        List<String> words = wordsByLetter.computeIfAbsent(key, k -> new ArrayList<>());
        if (!words.contains(word)) {
            words.add(word);
            Collections.sort(words);
        }
    }

    public void printAllLettersAndWords() {
        for (Map.Entry<Character, List<String>> entry : wordsByLetter.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void printWordsForLetter(char letter) {
        char key = Character.toUpperCase(letter);
        List<String> words = wordsByLetter.get(key);
        if (words == null || words.isEmpty()) {
            System.out.println("No words found starting with '" + letter + "'");
        } else {
            System.out.println(key + " -> " + words);
        }
    }}
