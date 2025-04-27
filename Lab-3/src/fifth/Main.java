package fifth;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final Set<String> KEYWORDS = Set.of(
            "computer", "network", "internet", "software", "hardware",
            "program", "database", "server", "cloud", "security", "data",
            "algorithm", "technology", "system", "application"
    );

    public static void main(String[] args) {
        List<String> files = List.of(
                "The_freedom_of_the_seas.txt",
                "The_Minute_Boys_of_Philadelphia.txt",
                "words_10002.txt",
                "sample-1MB.txt",
                "sample-10MB.txt",
                "sample-100MB.txt"
        );

        ForkJoinPool pool = ForkJoinPool.commonPool();
        Map<String, Integer> matchedFiles = pool.invoke(new SearchDocumentsTask(files, 0, files.size(), KEYWORDS));

        System.out.println("Documents containing IT keywords:");
        if (matchedFiles.isEmpty()) {
            System.out.println("None found.");
        } else {
            matchedFiles.forEach((file, count) ->
                    System.out.println("File: " + file + " - " + count + " keywords found")
            );
        }
    }
}
