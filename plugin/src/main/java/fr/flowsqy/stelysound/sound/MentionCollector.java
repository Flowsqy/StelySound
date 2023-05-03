package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MentionCollector {

    // First bit -> match without case, Second bit -> match with case
    public int getMention(@NotNull String researched, @NotNull List<String> words) {
        int mention = 0;
        for (String word : words) {
            if (mention == 0 && word.equalsIgnoreCase(researched)) {
                mention |= 1;
            }
            if (!word.equals(researched)) {
                continue;
            }
            mention |= 2;
            break;
        }
        return mention;
    }

}
