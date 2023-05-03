package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record OrganizedSounds(@NotNull SendableSound[] namesCaseSensitiveSounds,
                              @NotNull SendableSound[] namesCaseInsensitiveSounds,
                              @NotNull WordedSounds[] wordedSounds) {
}