package second;

import java.util.Arrays;

public record Stats(long words,
                    long sumLen,
                    long sumSq,
                    int min,
                    int max,
                    long[] hist) {

    public static Stats empty(int buckets) {
        return new Stats(0, 0, 0, Integer.MAX_VALUE, 0, new long[buckets]);
    }

    public Stats merge(Stats o) {
        long[] h = Arrays.copyOf(hist, hist.length);
        for (int i = 0; i < h.length; i++) h[i] += o.hist[i];
        return new Stats(words + o.words,
                sumLen + o.sumLen,
                sumSq + o.sumSq,
                Math.min(min, o.min),
                Math.max(max, o.max),
                h);
    }

    public double mean()     { return words == 0 ? 0 : (double) sumLen / words; }
    public double variance() { return words == 0 ? 0 : (double) sumSq / words - Math.pow(mean(), 2); }
    public double stdDev()   { return Math.sqrt(variance()); }
}
