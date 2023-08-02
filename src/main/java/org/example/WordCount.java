package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public class WordCount {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please pass the file name as an argument to the program");
        }

        WordCount wordCount = new WordCount();
        Map<String, Integer> wordCountMap = wordCount.getWordCountMap(args[0]);
        wordCount.printWordCountJson(wordCountMap);
    }

    /**
     * Prints Word cound in json format
     * @param wordCountMap
     */
    private void printWordCountJson(Map<String, Integer> wordCountMap) {
        System.out.println(new Gson().toJson(wordCountMap));
    }

    /**
     * Gets word count map
     * @param filePath
     * @return Map<String, Integer>
     */
    Map<String, Integer> getWordCountMap(String filePath) {
        Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                while (tokenizer.hasMoreTokens()) {
                    getWordCounts(tokenizer, wordCountMap);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException occurred" + e);
            throw new RuntimeException(e);
        }
        return wordCountMap;
    }

    /**
     * Get Word count and put it in the map
     * @param tokenizer
     * @param wordCountMap
     */
    void getWordCounts(StringTokenizer tokenizer, Map<String, Integer> wordCountMap) {
        String currentWord = tokenizer.nextToken().toLowerCase().replaceAll("[^A-Za-z]", "");
        if (!currentWord.isEmpty()) {
            if (wordCountMap.containsKey(currentWord)) {
                wordCountMap.put(currentWord, wordCountMap.get(currentWord) + 1);
            } else {
                wordCountMap.put(currentWord, 1);
            }
        }
    }
}