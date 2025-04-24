package second;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int BUCKETS = 31;

    public static void main(String[] args) throws IOException {
        String[] files = {
                "sample-1MB.txt",
                "sample-10MB.txt",
                "sample-100MB.txt"
        };

        System.out.printf("%-20s %10s %10s %10s%n", "File", "T_seq (ms)", "T_par (ms)", "Speed-up");

        for (String filename : files) {
            String text = Files.readString(Path.of(filename));

            // Послідовна версія
            long t1 = System.nanoTime();
            Stats seq = WordLenTask.analyzeSequential(text, BUCKETS);
            long t2 = System.nanoTime();

            // Паралельна версія
            long t3 = System.nanoTime();
            Stats par = ForkJoinPool.commonPool()
                    .invoke(new WordLenTask(text, 0, text.length(), BUCKETS));
            long t4 = System.nanoTime();

            assert seq.words() == par.words(); // sanity-check

            double tSeqMs = (t2 - t1) / 1e6;
            double tParMs = (t4 - t3) / 1e6;
            double speedup = tSeqMs / tParMs;

            System.out.printf("%-20s %10.2f %10.2f %10.2f%n", filename, tSeqMs, tParMs, speedup);
        }
    }
}
