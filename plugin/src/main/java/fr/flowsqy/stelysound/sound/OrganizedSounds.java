package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record OrganizedSounds(@NotNull CaseSpecifiedSounds nameSounds, @NotNull WordedSounds[] wordedSounds) {
}