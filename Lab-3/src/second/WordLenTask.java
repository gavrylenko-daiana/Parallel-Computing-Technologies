package second;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.RecursiveTask;

public class WordLenTask extends RecursiveTask<Stats> {

    private static final int CHUNK = 100_000;
    private static final Pattern WORD = Pattern.compile("\\p{L}+");

    private final CharSequence text;
    private final int from, to;
    private final int buckets;

    public WordLenTask(CharSequence text, int from, int to, int buckets) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.buckets = buckets;
    }

    @Override
    protected Stats compute() {
        if (to - from <= CHUNK) {
            return computeDirect();
        }
        int mid = (from + to) >>> 1;
        WordLenTask left = new WordLenTask(text, from, mid, buckets);
        WordLenTask right = new WordLenTask(text, mid, to, buckets);
        left.fork();
        Stats r = right.compute();
        Stats l = left.join();
        return l.merge(r);
    }

    public Stats computeDirect() {
        Matcher m = WORD.matcher(text.subSequence(from, to));
        Stats s = Stats.empty(buckets);
        while (m.find()) {
            int len = m.end() - m.start();
            int idx = Math.min(len, buckets - 1);
            long[] h = s.hist();
            h[idx]++;
            s = new Stats(s.words() + 1,
                    s.sumLen() + len,
                    s.sumSq() + (long) len * len,
                    Math.min(s.min(), len),
                    Math.max(s.max(), len),
                    h);
        }
        return s;
    }

    public static Stats analyzeSequential(CharSequence text, int buckets) {
        return new WordLenTask(text, 0, text.length(), buckets).computeDirect();
    }
}
