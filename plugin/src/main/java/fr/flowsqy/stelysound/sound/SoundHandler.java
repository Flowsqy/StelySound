package fr.flowsqy.stelysound.sound;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SoundHandler {

    private final WordsCollector wordsCollector;
    private final WordedSoundsCollector wordedSoundsCollector;
    private final OrganizedSounds organizedSounds;

    public SoundHandler(@NotNull WordsCollector wordsCollector, @NotNull WordedSoundsCollector wordedSoundsCollector, @NotNull OrganizedSounds organizedSounds) {
        this.wordsCollector = wordsCollector;
        this.wordedSoundsCollector = wordedSoundsCollector;
        this.organizedSounds = organizedSounds;
    }

    public void handle(@NotNull Collection<? extends Player> receivers, @NotNull String message) {
        final List<String> words = wordsCollector.getWords(message);
        final Set<SendableSound> wordedSounds = wordedSoundsCollector.getSendableSounds(words);

        final MentionCollector mentionCollector = new MentionCollector();
        for (Player receiver : receivers) {
            final Set<SendableSound> sendableSounds = new HashSet<>();
            final int mention = mentionCollector.getMention(receiver.getName(), words);
            if ((mention & 2) == 2) {
                sendableSounds.addAll(Arrays.asList(organizedSounds.namesCaseSensitiveSounds()));
            }
            if ((mention & 1) == 1) {
                sendableSounds.addAll(Arrays.asList(organizedSounds.namesCaseInsensitiveSounds()));
            }
            sendableSounds.addAll(wordedSounds);

            for (SendableSound sendableSound : sendableSounds) {
                sendableSound.sendIfNeeded(receiver);
            }
        }
    }

}
