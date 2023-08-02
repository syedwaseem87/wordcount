package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordCountTest {

    WordCount wordCount = new WordCount();

    @Test
    public void testGetWordCountMap() {
        Map<String, Integer> wordCountMap = wordCount.getWordCountMap("gpl-3.0.txt");
        Assertions.assertNotNull(wordCountMap);
        assertTrue(wordCountMap.containsKey("corresponding"));
    }

    @Test
    public void testGetWordCountMapWhenFileIsNotPresent() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> wordCount.getWordCountMap("test.txt"),
                "Expected getWordCountMap() to throw, but it didn't"
        );

    }

    @Test
    public void testGetWordCounts() {
        Map<String, Integer> wordCountMap = new HashMap<>();
        StringTokenizer tokenizer1 = new StringTokenizer("123!", " ");
        wordCount.getWordCounts(tokenizer1, wordCountMap);
        Assertions.assertTrue(wordCountMap.isEmpty());
        wordCountMap.put("this", 1);
        StringTokenizer tokenizer = new StringTokenizer("This, is", " ");
        wordCount.getWordCounts(tokenizer, wordCountMap);
        Assertions.assertNotNull(wordCountMap);
        assertEquals(2, wordCountMap.get("this"));
        StringTokenizer tokenizer2 = new StringTokenizer("That is", " ");
        wordCount.getWordCounts(tokenizer2, wordCountMap);
        assertEquals(1, wordCountMap.get("that"));
    }

}
