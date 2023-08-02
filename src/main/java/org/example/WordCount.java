package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordCount {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please pass the file name as an argument to the program");
        }

        WordCount wordCount = new WordCount();
        Map<String, Integer> wordCounts = wordCount.getWordCountMap(args[0]);
        wordCount.printWordCountJson(wordCounts);
    }

    void printWordCountJson(Map<String, Integer> wordCounts) {
        System.out.println(new Gson().toJson(wordCounts));
    }

    Map<String, Integer> getWordCountMap(String filePath) {
        Map<String, Integer> wordCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                while (tokenizer.hasMoreTokens()) {
                    getWordCounts(tokenizer, wordCounts);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException occurred" + e);
            throw new RuntimeException(e);
        }
        return wordCounts;
    }

    void getWordCounts(StringTokenizer tokenizer, Map<String, Integer> wordCounts) {
        String currentWord = tokenizer.nextToken().toLowerCase().replaceAll("[^A-Za-z]", "");
        if (!currentWord.isEmpty()) {
            if (wordCounts.containsKey(currentWord)) {
                wordCounts.put(currentWord, wordCounts.get(currentWord) + 1);
            } else {
                wordCounts.put(currentWord, 1);
            }
        }
    }
}