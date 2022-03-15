package ro.home.MostFrequestCharacterCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MostFrequentCharacterCounter implements CharacterCounter {

    private String message;

    public MostFrequentCharacterCounter(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public Character getCharacter() {

        int maxCount = 0;
        char maxChar = 0;

// Step 1 : Define one HashMap object called charCountMap with characters as its key and their occurrences as value.
        HashMap<Character, Integer> charCountMap = new HashMap<>();

// Step 2 : Convert given inputString to charArray after removing white spaces
        message = message.replaceAll(" ", "").toLowerCase();
        char[] characterArray = message.toCharArray();

// Step 3 : For each character in charArray, check whether it is present in charCountMap.
// If it is present, increment its value by 1 and if it is not present, insert that character into charCountMap with 1 as its value.
        for (char c : characterArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

// Step 4 : After getting all characters and their count into charCountMap, get the maximum occurring character and its count as below.
        Set<Map.Entry<Character, Integer>> entrySet = charCountMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxChar = entry.getKey();
            }
        }
        return maxChar;
    }
}
