package first;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int BUCKETS = 31;

    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("words_10002.txt"));

        Stats stats = ForkJoinPool.commonPool()
                .invoke(new WordLenTask(text, 0, text.length(), BUCKETS));

        System.out.printf("""
                Words     : %d
                Avg len   : %.2f symbols
                Min / Max : %d / %d
                σ         : %.2f
                %n""",
                stats.words(), stats.mean(),
                stats.min(), stats.max(),
                stats.stdDev());
    }
}
