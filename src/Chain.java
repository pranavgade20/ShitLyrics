import java.util.HashMap;
import java.util.Map;

public class Chain {
    private HashMap<Word, Integer> chain;
    private HashMap<Word, Double> computed;

    private Word prev;
    public Chain(String firstWord) {
        prev = new Word(firstWord);
        chain = new HashMap<>();
        chain.put(prev, 1);
    }
    public void train(String word) {
        Word w = null;

        if (!chain.containsKey(new Word(word))) {
            w = new Word(word);
            chain.put(w, 1);
        } else {
            for (Word key : chain.keySet()) {
                if (word.hashCode() == key.hashCode()) { // ik this is terribly inefficient but meh
                    w = key;
                    int v = chain.get(key) + 1;
                    chain.replace(key, v);
                    break;
                }
            }
        }

        prev.train(w);

        prev = w;
    }

    public void completeTraining() {
        for (Word w : chain.keySet()) {
            w.completeTraining();
        }

        computed = new HashMap<>();
        double total = 0;
        for (int v : chain.values()) {
            total += v;
        }


        double cumulative = 0;
        for (Map.Entry<Word, Integer> entry : chain.entrySet()) {
            Word key = entry.getKey();
            int value = entry.getValue();
            cumulative += (double)value / total;
            computed.put(key, cumulative);
        }

    }

    public Word predict(Word prev) {
        if (!chain.containsKey(prev)) {
            return null;
        }

        double selection = Math.random();
        for (Map.Entry<Word, Double> entry : prev.computed.entrySet()) {
            Double value = entry.getValue();
            if (value > selection) return entry.getKey();
        }
        return null;
    }

    public Word predictFirst() {
        double selection = Math.random();
        for (Map.Entry<Word, Double> entry : computed.entrySet()) {
            Double value = entry.getValue();
            if (value > selection) {
                return entry.getKey();
            }
        }
        return null;
    }
}
