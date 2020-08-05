import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Chain chain = new Chain("hello");

        InputStreamReader reader = new InputStreamReader(new FileInputStream("cleaned.txt"));
        Scanner scanner = new Scanner(reader);
        String line;

        CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();
        int cnt = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            cnt ++;

            if (!line.startsWith("[") && line.chars().allMatch(c -> (c>=0x20 && c<0x7f))) {
                for (String word : line.strip().split("[,. ]")) {
                    if (!word.isEmpty()) chain.train(word.toLowerCase().strip());
                }
            }
            if (!line.isEmpty() && !line.isBlank()) chain.train("\n");

            if (cnt%200 == 0) {
                System.out.println((double)cnt/200.0);
            }
        }

        chain.completeTraining();

        System.out.println("Done training");

        Word prev = chain.predictFirst();
        for (int i = 0; i < 10000; i++) {
            Word next = chain.predict(prev);
            System.out.print(prev.text + " ");
            prev = next;
        }
    }
}
