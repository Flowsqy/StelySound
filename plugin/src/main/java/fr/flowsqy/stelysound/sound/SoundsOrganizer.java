package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SoundsOrganizer {

    public OrganizedSounds organize(@NotNull List<ConfiguredSound> configuredSounds) {
        final Map<String, CaseSpecifiedSoundSets> wordedSounds = new HashMap<>();
        final CaseSpecifiedSoundSets namesSounds = new CaseSpecifiedSoundSets();
        for (ConfiguredSound configuredSound : configuredSounds) {
            final SendableSound sendableSound = configuredSound.sendableSound();
            if (configuredSound.triggeredByNames()) {
                final Set<SendableSound> sounds = configuredSound.caseSensitive() ? namesSounds.caseSensitive() : namesSounds.caseInsensitive();
                sounds.add(sendableSound);
            }
            for (String word : configuredSound.words()) {
                final CaseSpecifiedSoundSets wordSoundSet = wordedSounds.computeIfAbsent(word, s -> new CaseSpecifiedSoundSets());
                final Set<SendableSound> sounds = configuredSound.caseSensitive() ? wordSoundSet.caseSensitive() : wordSoundSet.caseInsensitive();
                sounds.add(sendableSound);
            }
        }

        return new OrganizedSounds(namesSounds.toCaseSpecifiedSounds(), transformWordedSoundsMap(wordedSounds));
    }

    @NotNull
    private WordedSounds[] transformWordedSoundsMap(@NotNull Map<String, CaseSpecifiedSoundSets> wordedSoundsData) {
        final WordedSounds[] wordedSounds = new WordedSounds[wordedSoundsData.size()];
        int index = 0;
        for (Map.Entry<String, CaseSpecifiedSoundSets> wordedSoundData : wordedSoundsData.entrySet()) {
            wordedSounds[index++] = new WordedSounds(wordedSoundData.getKey(), wordedSoundData.getValue().toCaseSpecifiedSounds());
        }
        return wordedSounds;
    }

    private record CaseSpecifiedSoundSets(@NotNull Set<SendableSound> caseSensitive,
                                          @NotNull Set<SendableSound> caseInsensitive) {

        public CaseSpecifiedSoundSets() {
            this(new HashSet<>(), new HashSet<>());
        }

        public CaseSpecifiedSounds toCaseSpecifiedSounds() {
            return new CaseSpecifiedSounds(caseSensitive.toArray(new SendableSound[0]), caseInsensitive().toArray(new SendableSound[0]));
        }

    }

}
