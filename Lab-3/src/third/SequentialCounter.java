package third;

public class SequentialCounter {

    public static long countOccurrences(CharSequence text, String word) {
        long count = 0;
        int i = 0;
        while (i <= text.length() - word.length()) {
            if (isWordAt(text, i, word)) {
                count++;
                i += word.length();
            } else {
                i++;
            }
        }
        return count;
    }

    public static boolean isWordAt(CharSequence text, int pos, String word) {
        int len = word.length();
        if (pos + len > text.length()) return false;
        for (int i = 0; i < len; i++) {
            if (text.charAt(pos + i) != word.charAt(i)) return false;
        }
        boolean before = pos == 0 || !Character.isLetterOrDigit(text.charAt(pos - 1));
        boolean after  = pos + len == text.length() || !Character.isLetterOrDigit(text.charAt(pos + len));
        return before && after;
    }
}
