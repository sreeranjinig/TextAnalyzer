package org.textAnalytics;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class TextAnalyzerTest {

    private final TextAnalyzer textAnalyzer = new TextAnalyzer();

    @Test
    void testReadFileCountSortedWords() throws URISyntaxException, IOException {
        String fileContent = String.valueOf(Path.of(Objects.requireNonNull(getClass()
                                                        .getClassLoader()
                                                        .getResource("example.txt"))
                        .toURI()));

        Map<String, Integer> countMap = textAnalyzer.countValidWordsByReadingFile(fileContent);
        assertFalse(countMap.isEmpty());
        assertEquals(8, countMap.size());
        assertAll(
                () -> assertEquals(4, countMap.get("apple")),
                () -> assertEquals(2, countMap.get("banana")),
                () -> assertEquals(2, countMap.get("fruit"))
        );
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    @Test
    void testReadWhenFileIsEmpty() throws URISyntaxException, IOException {
        String fileContent = String.valueOf(Path.of(Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource("empty.txt"))
                .toURI()));

        Map<String, Integer> countMap = textAnalyzer.countValidWordsByReadingFile(fileContent);
        assertTrue(countMap.isEmpty());
    }
}
