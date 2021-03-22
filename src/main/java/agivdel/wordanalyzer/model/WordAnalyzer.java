package agivdel.wordanalyzer.model;

import java.util.*;

public class WordAnalyzer {
    public Map<Character, Integer> getMostCommonLetter(String word) {
        if (word.length() == 1 && !Character.isLetter(word.charAt(0))) {
            throw new IllegalArgumentException(String.format("The word '%s' does not contain any letter.", word));
        }
        if (word.length() == 1 && Character.isLetter(word.charAt(0))) {
            return Map.of(word.charAt(0), 1);
        }
        Map<Character, Integer> counts = countLettersFromWord(word);
        int maxCount = maxCount(counts);
        List<Character> letters = getMostCommonLetters(counts, maxCount);
        char mostCommonLetter = getLastMostCommonLetter(letters, word);
        return Map.of(mostCommonLetter, maxCount);
    }

    private Map<Character, Integer> countLettersFromWord(String word) {
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : word.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                counts.merge(c, 1, Integer::sum);
            }
        }
        return counts;
    }

    private int maxCount(Map<Character, Integer> counts) {
        return counts.values().stream().max(Integer::compareTo).get();
    }

    private List<Character> getMostCommonLetters(Map<Character, Integer> counts, int maxCount) {
        List<Character> letters = new ArrayList<>();
        for (var entry : counts.entrySet()) {
            if (maxCount == entry.getValue()) {
                letters.add(entry.getKey());//если несколько value равны maxCount, все их ключи будут помещены в список letters
            }
        }
        return letters;
    }

    private char getLastMostCommonLetter(List<Character> letters, String word) {
        if (letters.size() == 1) {
            return letters.get(0);
        }
        int maxIndex = 0;

        //var.N1
        maxIndex = letters.stream().mapToInt(word::lastIndexOf).max().getAsInt();

        //var.N2
        for (Character c : letters) {
            int currentIndex = word.lastIndexOf(c);
            maxIndex = Math.max(currentIndex, maxIndex);
        }

        return word.charAt(maxIndex);
    }
}