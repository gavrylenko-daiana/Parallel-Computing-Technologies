package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        generate("sample-1MB.txt", 1 * 1024 * 1024);
        generate("sample-10MB.txt", 10 * 1024 * 1024);
        generate("sample-100MB.txt", 100 * 1024 * 1024);
    }

    public static void generate(String filename, int targetSizeBytes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            int size = 0;
            while (size < targetSizeBytes) {
                int len = 1 + RANDOM.nextInt(30); // довжина слова 1–30
                StringBuilder word = new StringBuilder(len);
                for (int i = 0; i < len; i++) {
                    word.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
                }
                writer.write(word.toString());
                writer.write(' ');
                size += len + 1;
            }
            System.out.println("Created: " + filename);
        }
    }
}
