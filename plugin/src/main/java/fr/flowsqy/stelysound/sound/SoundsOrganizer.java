package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SoundsOrganizer {

    public OrganizedSounds organize(@NotNull List<ConfiguredSound> configuredSounds) {
        final Map<String, Set<SendableSound>> wordsCaseSensitiveSounds = new HashMap<>();
        final Map<String, Set<SendableSound>> wordsCaseInsensitiveSounds = new HashMap<>();
        final Set<SendableSound> namesCaseSensitiveSounds = new HashSet<>();
        final Set<SendableSound> namesCaseInsensitiveSounds = new HashSet<>();
        for (ConfiguredSound configuredSound : configuredSounds) {
            final SendableSound sendableSound = configuredSound.sendableSound();
            if (configuredSound.triggeredByNames()) {
                if (configuredSound.caseSensitive()) {
                    namesCaseSensitiveSounds.add(sendableSound);
                } else {
                    namesCaseInsensitiveSounds.add(sendableSound);
                }
            }
            for (String word : configuredSound.words()) {
                final Set<SendableSound> sounds;
                if (configuredSound.caseSensitive()) {
                    sounds = wordsCaseSensitiveSounds.computeIfAbsent(word, o -> new HashSet<>());
                } else {
                    sounds = wordsCaseInsensitiveSounds.computeIfAbsent(word, o -> new HashSet<>());
                }
                sounds.add(sendableSound);
            }
        }

        final Set<WordedSounds> wordedSounds = new HashSet<>(transformWordedSoundsMap(wordsCaseSensitiveSounds, true));
        wordedSounds.addAll(transformWordedSoundsMap(wordsCaseInsensitiveSounds, false));

        return new OrganizedSounds(namesCaseSensitiveSounds.toArray(new SendableSound[0]), namesCaseInsensitiveSounds.toArray(new SendableSound[0]), wordedSounds.toArray(new WordedSounds[0]));
    }

    @NotNull
    private List<WordedSounds> transformWordedSoundsMap(@NotNull Map<String, Set<SendableSound>> wordedSoundsData, boolean caseSensitive) {
        final List<WordedSounds> wordedSounds = new LinkedList<>();
        for (Map.Entry<String, Set<SendableSound>> wordedSoundData : wordedSoundsData.entrySet()) {
            final SendableSound[] sendableSounds = wordedSoundData.getValue().toArray(new SendableSound[0]);
            if (sendableSounds.length == 0) {
                continue;
            }
            wordedSounds.add(new WordedSounds(wordedSoundData.getKey(), new CaseSensitiveSounds(caseSensitive, sendableSounds)));
        }
        return wordedSounds;
    }

}
