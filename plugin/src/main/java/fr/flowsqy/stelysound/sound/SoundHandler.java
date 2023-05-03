package fr.flowsqy.stelysound.sound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SoundHandler {

    private final WordsCollector wordsCollector;
    private final OrganizedSounds organizedSounds;

    public SoundHandler(@NotNull WordsCollector wordsCollector, @NotNull OrganizedSounds organizedSounds) {
        this.wordsCollector = wordsCollector;
        this.organizedSounds = organizedSounds;
    }

    public void asyncHandle(@NotNull Plugin plugin, @NotNull Collection<? extends Player> receivers, @NotNull String message) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> handle(new ArrayList<>(receivers), message));
    }

    public void handle(@NotNull Collection<? extends Player> receivers, @NotNull String message) {
        final List<String> words = wordsCollector.getWords(message);
        final Set<SendableSound> toSendWordedSound = new HashSet<>();
        final MentionCollector mentionCollector = new MentionCollector();
        final SoundsCollector soundsCollector = new SoundsCollector();
        for (WordedSounds wordedSounds : this.organizedSounds.wordedSounds()) {
            soundsCollector.getSounds(mentionCollector, wordedSounds.word(), words, wordedSounds.caseSpecifiedSounds(), toSendWordedSound);
        }
        for (Player receiver : receivers) {
            final Set<SendableSound> toSendPlayerSound = new HashSet<>(toSendWordedSound);
            soundsCollector.getSounds(mentionCollector, receiver.getName(), words, organizedSounds.nameSounds(), toSendPlayerSound);

            for (SendableSound sendableSound : toSendPlayerSound) {
                sendableSound.sendIfNeeded(receiver);
            }
        }
    }

}
