package fifth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchDocumentsTask extends RecursiveTask<Map<String, Integer>> {

    private static final int THRESHOLD = 1;
    private static final Pattern WORD = Pattern.compile("\\p{L}+");

    private final List<String> files;
    private final int from, to;
    private final Set<String> keywords;

    public SearchDocumentsTask(List<String> files, int from, int to, Set<String> keywords) {
        this.files = files;
        this.from = from;
        this.to = to;
        this.keywords = keywords;
    }

    @Override
    protected Map<String, Integer> compute() {
        if (to - from <= THRESHOLD) {
            return processFiles();
        }
        int mid = (from + to) / 2;
        SearchDocumentsTask left = new SearchDocumentsTask(files, from, mid, keywords);
        SearchDocumentsTask right = new SearchDocumentsTask(files, mid, to, keywords);
        left.fork();
        Map<String, Integer> rightResult = right.compute();
        Map<String, Integer> leftResult = left.join();
        leftResult.putAll(rightResult);
        return leftResult;
    }

    private Map<String, Integer> processFiles() {
        Map<String, Integer> matched = new HashMap<>();
        for (int i = from; i < to; i++) {
            String fileName = files.get(i);
            try {
                String text = Files.readString(Path.of(fileName));
                int keywordCount = countKeywords(text);
                if (keywordCount > 0) {
                    matched.put(fileName, keywordCount);
                }
            } catch (IOException e) {
                System.err.println("Cannot read file: " + fileName);
            }
        }
        return matched;
    }

    private int countKeywords(String text) {
        Matcher matcher = WORD.matcher(text);
        int count = 0;
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            if (keywords.contains(word)) {
                count++;
            }
        }
        return count;
    }
}
