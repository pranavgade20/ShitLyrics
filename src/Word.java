import java.util.HashMap;
import java.util.Map;

public class Word {
    public String text;
    public HashMap<Word, Integer> children;
    public HashMap<Word, Double> computed;

    public Word(String text) {
        this.text = text;
        children = new HashMap<>();
    }

    public void train(Word child) {
        if (children.containsKey(child)) {
            int value = children.get(child) + 1;
            children.replace(child, value);
        } else {
            children.put(child, 1);
        }
    }

    public void completeTraining() {
        computed = new HashMap<>();
        double total = 0;
        for (int v : children.values()) {
            total += v;
        }

        double cumulative = 0;
        for (Map.Entry<Word, Integer> entry : children.entrySet()) {
            Word key = entry.getKey();
            int value = entry.getValue();
            cumulative += (double)value / total;
            computed.put(key, cumulative);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return text.equals(word.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
