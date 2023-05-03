package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordedSoundsCollector {

    private final WordedSounds[] wordedSounds;

    public WordedSoundsCollector(@NotNull WordedSounds[] wordedSounds) {
        this.wordedSounds = wordedSounds;
    }

    @NotNull
    public Set<SendableSound> getSendableSounds(@NotNull List<String> words) {
        final Set<SendableSound> sendableSounds = new HashSet<>();
        for (String word : words) {
            for (WordedSounds wordedSounds : this.wordedSounds) {
                if (wordedSounds.isValid(word)) {
                    sendableSounds.addAll(Arrays.asList(wordedSounds.caseSensitiveSounds().sendableSounds()));
                }
            }
        }
        return sendableSounds;
    }

}
