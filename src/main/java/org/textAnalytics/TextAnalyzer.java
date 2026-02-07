package org.textAnalytics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

    /*
        Reads file from a path into a String
     */
    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }


    public Map<String, Integer> countValidWordsByReadingFile(String filePath) throws IOException {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        String fileContent = readFile(filePath);

        if(fileContent==null || fileContent.isEmpty()){
            return wordFrequencyMap;
        }

       Arrays.stream(fileContent.trim().toLowerCase().split("\\s+"))
               .filter(words ->!words.isBlank())
               .forEach(word -> wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0)+1));

        return wordFrequencyMap.entrySet()
               .stream().sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed()
                       .thenComparing(Map.Entry::getKey))
               .collect(Collectors.toMap(Map.Entry ::getKey, Map.Entry ::getValue, (e1, e2) ->e1, LinkedHashMap :: new));

    }

}
