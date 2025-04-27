package fourth;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonWordsTask extends RecursiveTask<Set<String>> {

    private static final int CHUNK = 100_000;
    private static final Pattern WORD = Pattern.compile("\\p{L}+");

    private final CharSequence text;
    private final int from, to;

    public CommonWordsTask(CharSequence text, int from, int to) {
        this.text = text;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Set<String> compute() {
        if (to - from <= CHUNK) {
            return extractWords();
        }
        int mid = (from + to) / 2;
        CommonWordsTask left = new CommonWordsTask(text, from, mid);
        CommonWordsTask right = new CommonWordsTask(text, mid, to);
        left.fork();
        Set<String> rightSet = right.compute();
        Set<String> leftSet = left.join();
        leftSet.addAll(rightSet);
        return leftSet;
    }

    private Set<String> extractWords() {
        Matcher m = WORD.matcher(text.subSequence(from, to));
        Set<String> words = new HashSet<>();
        while (m.find()) {
            String word = m.group().toLowerCase();
            if (word.length() > 1) {
                words.add(word);
            }
        }
        return words;
    }
}
