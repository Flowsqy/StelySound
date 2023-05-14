package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiPredicate;

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

    public int getMention(@NotNull String[] researched, @NotNull String[] words) {
        int mention = 0;
        if (isMentioned(researched, words, String::equals)) {
            mention = set(CASE_SENSITIVE, mention);
            mention = set(CASE_INSENSITIVE, mention);
        } else if (isMentioned(researched, words, String::equalsIgnoreCase)) {
            mention = set(CASE_INSENSITIVE, mention);
        }
        return mention;
    }

    private boolean isMentioned(@NotNull String[] researched, @NotNull String[] words, @NotNull BiPredicate<String, String> equalityPredicate) {
        final int wordsLength = words.length;
        final int researchLength = researched.length;
        for (int wordIndex = 0; wordIndex < (wordsLength - researchLength + 1); wordIndex++) {
            boolean flag = true;
            for (int searchIndex = 0; searchIndex < researchLength; searchIndex++) {
                if (equalityPredicate.test(researched[searchIndex], words[searchIndex + wordIndex])) {
                    continue;
                }
                flag = false;
                break;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    private int set(int mention, int type) {
        return mention | (1 << type);
    }

    private boolean get(int mention, int type) {
        return (mention & (1 << type)) != 0;
    }

    public boolean matchCaseSensitive(int mention) {
        return get(mention, CASE_SENSITIVE);
    }

    public boolean matchCaseInsensitive(int mention) {
        return get(mention, CASE_INSENSITIVE);
    }

}
