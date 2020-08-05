import java.io.*;
import java.util.Arrays;

/**
 * Removes non english words form input.
 */
public class Cleaner {
    public static void main(String[] args) throws Exception{
        BufferedReader dictionary = new BufferedReader(new FileReader("words.txt"));
        int size = 466551;
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            words[i] = dictionary.readLine().toLowerCase();
        }
        Arrays.sort(words);

        BufferedReader lyrics = new BufferedReader(new FileReader("lyrics.txt"));
        BufferedWriter cleaned = new BufferedWriter(new FileWriter("cleaned.txt"));

        String line;

        while ((line = lyrics.readLine()) != null) {
            StringBuilder toWrite = new StringBuilder();
            boolean write = true;
            if (!line.startsWith("[")) {
                for (String word : line.strip().split("[,. ]")) {
                    if (!word.isEmpty()) {
                        if (Arrays.binarySearch(words, word.strip().toLowerCase().replaceAll("['\"?]", "")) >= 0) {
                            toWrite.append(word);
                            toWrite.append(" ");
                        } else write = false;
                    }
                }
            }
            if (toWrite.length() != 0 && write) {
                toWrite.setCharAt(toWrite.length()-1, '\n');
                cleaned.write(toWrite.toString());
            }
        }
    }
}
