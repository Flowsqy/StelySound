package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class WordsCollector {

    private final char[] separators;

    public WordsCollector(char @NotNull [] separators) {
        this.separators = separators;
    }

    @NotNull
    public List<String> getWords(@NotNull String message) {
        final List<String> words = new LinkedList<>();
        int start = 0;
        int pos = 0;
        for (; pos < message.length(); pos++) {
            final char c = message.charAt(pos);
            if (!isSeparator(c)) {
                continue;
            }
            if (start == pos) {
                start++;
                continue;
            }
            words.add(message.substring(start, pos));
            start = pos + 1;
        }
        if (start != pos) {
            words.add(message.substring(start, pos));
        }
        return words;
    }

    private boolean isSeparator(char c) {
        for (char separator : separators) {
            if (c == separator) {
                return true;
            }
        }
        return false;
    }

}
