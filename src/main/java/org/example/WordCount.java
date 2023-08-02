package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WordCount {
    public static void main(String[] args) {
        Map<String, Integer> wordCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("gpl-3.0.txt")), StandardCharsets.UTF_8))) {
            String line;

            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                while (tokenizer.hasMoreTokens()) {
                    String currentWord = tokenizer.nextToken().toLowerCase();
                    if (wordCounts.containsKey(currentWord)) {
                        wordCounts.put(currentWord, wordCounts.get(currentWord) + 1);
                    } else {
                        wordCounts.put(currentWord, 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(wordCounts);
    }
}