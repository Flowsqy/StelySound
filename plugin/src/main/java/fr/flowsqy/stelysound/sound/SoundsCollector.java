package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SoundsCollector {

    public void getSounds(@NotNull MentionCollector mentionCollector, @NotNull String researched, @NotNull List<String> words, @NotNull CaseSpecifiedSounds caseSpecifiedSounds, @NotNull Set<SendableSound> output) {
        final int mention = mentionCollector.getMention(researched, words);
        if (mentionCollector.matchCaseSensitive(mention)) {
            output.addAll(Arrays.asList(caseSpecifiedSounds.caseSensitive()));
        }
        if (mentionCollector.matchCaseInsensitive(mention)) {
            output.addAll(Arrays.asList(caseSpecifiedSounds.caseInsensitive()));
        }
    }

}
