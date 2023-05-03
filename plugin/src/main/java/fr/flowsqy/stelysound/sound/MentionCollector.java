package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MentionCollector {

    private final int CASE_SENSITIVE = 0;
    private final int CASE_INSENSITIVE = 1;

    public int getMention(@NotNull String researched, @NotNull List<String> words) {
        int mention = 0;
        for (String word : words) {
            if (mention == 0 && word.equalsIgnoreCase(researched)) {
                mention = set(mention, CASE_INSENSITIVE);
            }
            if (!word.equals(researched)) {
                continue;
            }
            mention = set(mention, CASE_SENSITIVE);
            break;
        }
        return mention;
    }

    private int set(int mention, int type) {
        return mention | (1 << type);
    }

    private boolean get(int mention, int type) {
        return (mention & (1 << type)) == 1;
    }

    public boolean matchCaseSensitive(int mention) {
        return get(mention, CASE_SENSITIVE);
    }

    public boolean matchCaseInsensitive(int mention) {
        return get(mention, CASE_INSENSITIVE);
    }

}
