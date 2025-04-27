package fourth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> files = List.of(
                "The_freedom_of_the_seas.txt",
                "The_Minute_Boys_of_Philadelphia.txt"
        );

        ForkJoinPool pool = ForkJoinPool.commonPool();

        List<Set<String>> allWords = files.stream()
                .map(file -> {
                    try {
                        String text = Files.readString(Path.of(file));
                        return pool.invoke(new CommonWordsTask(text, 0, text.length()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        if (allWords.isEmpty()) {
            System.out.println("No files provided.");
            return;
        }

        // Перетин усіх множин
        Set<String> common = allWords.get(0);
        for (int i = 1; i < allWords.size(); i++) {
            common.retainAll(allWords.get(i));
        }

        System.out.println("Number of common words: " + common.size());
        System.out.println("Example common words: " +
                common.stream().limit(10).collect(Collectors.joining(", ")));
    }
}
