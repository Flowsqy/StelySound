package fr.flowsqy.stelysound.sound;

import org.jetbrains.annotations.NotNull;

public record CaseSpecifiedSounds(@NotNull SendableSound[] caseSensitive, @NotNull SendableSound[] caseInsensitive) {
}
