package third;

import java.util.concurrent.RecursiveTask;

public class WordCountTask extends RecursiveTask<Long> {

    private static final int CHUNK = 100_000;

    private final CharSequence text;
    private final int from, to;
    private final String target;

    public WordCountTask(CharSequence text, int from, int to, String target) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    @Override
    protected Long compute() {
        if (to - from <= CHUNK) {
            return countDirect();
        }
        int mid = (from + to) >>> 1;
        WordCountTask left = new WordCountTask(text, from, mid, target);
        WordCountTask right = new WordCountTask(text, mid, to, target);
        left.fork();
        long r = right.compute();
        long l = left.join();
        return l + r;
    }

    private long countDirect() {
        long count = 0;
        int i = from;
        while (i <= to - target.length()) {
            if (SequentialCounter.isWordAt(text, i, target)) {
                count++;
                i += target.length();
            } else {
                i++;
            }
        }
        return count;
    }
}
