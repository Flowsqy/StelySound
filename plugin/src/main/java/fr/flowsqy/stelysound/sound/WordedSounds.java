package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record WordedSounds(@NotNull String word, @NotNull CaseSensitiveSounds caseSensitiveSounds) {

    public boolean isValid(@NotNull String word) {
        return caseSensitiveSounds.isValid(this.word, word);
    }

}
