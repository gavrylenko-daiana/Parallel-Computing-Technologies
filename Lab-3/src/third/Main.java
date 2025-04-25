package third;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final String TARGET = "go";

    public static void main(String[] args) throws IOException {
        String[] files = {
                "sample-1MB.txt",
                "sample-10MB.txt",
                "sample-100MB.txt"
        };

        System.out.printf("Word: \"%s\"%n", TARGET);
        System.out.printf("%-20s %10s %10s %10s %10s %10s%n",
                "File", "SeqCnt", "ParCnt", "T_seq", "T_par", "Speed-up");

        for (String file : files) {
            String text = Files.readString(Path.of(file));

            long t1 = System.nanoTime();
            long seqCount = SequentialCounter.countOccurrences(text, TARGET);
            long t2 = System.nanoTime();

            long t3 = System.nanoTime();
            long parCount = ForkJoinPool.commonPool()
                    .invoke(new WordCountTask(text, 0, text.length(), TARGET));
            long t4 = System.nanoTime();

            if (seqCount != parCount) {
                throw new AssertionError("Mismatch: " + seqCount + " != " + parCount);
            }

            double tSeq = (t2 - t1) / 1e6;
            double tPar = (t4 - t3) / 1e6;
            double speedup = tSeq / tPar;

            System.out.printf("%-20s %10d %10d %10.2f %10.2f %10.2f%n",
                    file, seqCount, parCount, tSeq, tPar, speedup);
        }
    }
}
