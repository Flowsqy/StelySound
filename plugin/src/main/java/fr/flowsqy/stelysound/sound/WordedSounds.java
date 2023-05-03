package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record WordedSounds(@NotNull String word, @NotNull CaseSpecifiedSounds caseSensitiveSounds) {
}
